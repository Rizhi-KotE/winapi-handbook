package client.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.WinApiClass;
import model.WinApiFunction;
import org.reactfx.EventSource;
import org.reactfx.EventStream;
import org.reactfx.EventStreams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static javafx.collections.FXCollections.observableArrayList;


public abstract class ClassCreateForm extends VBox {

    final WinApiHandbookReactor reactor;
    final EventSource<ActionEvent> submit;
    TextField descriptionField;
    final ObservableList<FunctionCreateForm> functionCreateForms;
    TextField nameField;

    ClassCreateForm(WinApiHandbookReactor reactor) {
        this.reactor = reactor;
        this.functionCreateForms = observableArrayList();
        submit = new EventSource<>();
    }

    public void setup() {
        EventStream<String> nameBlock = createNameBlock();
        EventStream<String> descriptionBlock = createDescriptionBlock();
        EventStream<List<WinApiFunction>> functionsFields = createFunctionsFields();
        createSubmitButton();
        EventStreams.combine(nameBlock, descriptionBlock, functionsFields)
                .subscribe(t -> {
                    WinApiClass winApiClass =
                            new WinApiClass(null, t._1, t._2, "", t._3);
                    reactor.pushClass(winApiClass);
                });
    }

    private EventStream<String> createNameBlock() {
        Label name = new Label("Name");
        nameField = new TextField();
        reactor.getClassEventSource()
                .map(WinApiClass::getName)
                .feedTo(nameField.textProperty());


        VBox vBox = new VBox(name, nameField);
        getChildren().add(vBox);

        return submit.map(actionEvent -> nameField.getText());
    }

    private EventStream<String> createDescriptionBlock() {
        Label description = new Label("Description");
        descriptionField = new TextField();
        reactor.getClassEventSource()
                .map(WinApiClass::getDescription)
                .feedTo(descriptionField.textProperty());

        VBox vBox = new VBox(description, descriptionField);
        getChildren().add(vBox);

        return submit.map(e -> descriptionField.getText());
    }

    private EventStream<List<WinApiFunction>> createFunctionsFields() {
        VBox vBox = new VBox();
        reactor.getClassEventSource().map(WinApiClass::getFunctions)
                .map(f -> f.stream().map(this::functionCreateForm).collect(toList()))
                .hook(functionCreateForms::setAll)
                .subscribe(f -> vBox.getChildren().setAll(f));

        Function<ActionEvent, List<WinApiFunction>> a = e -> {
            List<EventSource<WinApiFunction>> collect = vBox.getChildren().stream()
                    .map(FunctionCreateForm.class::cast)
                    .map(FunctionCreateForm::getFunction)
                    .collect(toList());
            ObservableSet<EventSource<WinApiFunction>> eventSources = FXCollections.observableSet(new HashSet<>(collect));
            EventStream<WinApiFunction> merge = EventStreams.merge(eventSources);
            ArrayList<WinApiFunction> objects = new ArrayList<>();
            merge.subscribe(objects::add);
            return objects;
        };
        return submit.map(a);
    }

    ;

    private void createSubmitButton() {
        Button create = new Button("Create");
        create.setOnAction(submit::push);
    }

    private FunctionCreateForm functionCreateForm(WinApiFunction function) {
        FunctionCreateForm functionCreateForm = functionCreateForm();
        functionCreateForm.bindSubmit(submit);
        return functionCreateForm;
    }

    abstract FunctionCreateForm functionCreateForm();
}
