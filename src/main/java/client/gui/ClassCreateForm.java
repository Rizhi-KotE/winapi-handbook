package client.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Setter;
import model.WinApiClass;
import model.WinApiFunction;
import org.reactfx.EventSource;
import org.reactfx.EventStream;
import org.reactfx.EventStreams;

import java.util.List;

import static java.util.stream.Collectors.toList;


public abstract class ClassCreateForm extends VBox {

   final WinApiHandbookReactor reactor;
   final EventSource<ActionEvent> submit;

    ClassCreateForm(WinApiHandbookReactor reactor){
        this.reactor = reactor;
        submit = new EventSource<>();
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
        TextField nameField = new TextField();
        reactor.getClassEventSource()
                .map(WinApiClass::getName)
                .feedTo(nameField.textProperty());


        VBox vBox = new VBox(name, nameField);
        getChildren().add(vBox);

        return submit.map(actionEvent -> name.getText());
    }

    private EventStream<String> createDescriptionBlock() {
        Label description = new Label("Description");
        TextField descriptionField = new TextField();
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
                .subscribe(f -> vBox.getChildren().setAll(f));

        return submit.map(e -> vBox.getChildren().stream()
                .map(FunctionCreateForm.class::cast)
                .map(FunctionCreateForm::getFunction)
                .collect(toList()));
    }

    private void createSubmitButton() {
        Button create = new Button("Create");
        create.setOnAction(submit::push);
    }

    private FunctionCreateForm functionCreateForm(WinApiFunction function) {
        return functionCreateForm();
    }

    abstract FunctionCreateForm functionCreateForm();
}
