package client.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WinApiFunction;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.reactfx.EventStreams.combine;

public class FunctionCreateForm extends VBox {

    private final EventSource<WinApiFunction> function;
    private EventSource<ActionEvent> submit;
    private TextField nameField;
    private TextField descriptionField;
    private EventSource<WinApiFunction> outputFunction;

    FunctionCreateForm() {
        function = new EventSource<>();
        submit = new EventSource<>();
        outputFunction = new EventSource<>();
        EventStream<String> nameBlock = createNameBlock();
        EventStream<String> descriptionBlock = createDescriptionBlock();
        EventStream<List<String>> functionsFields = createFunctionsFields();

        combine(nameBlock, descriptionBlock, functionsFields)
                .subscribe(t -> {
                    WinApiFunction winApiFunction =
                            new WinApiFunction(null, t._1, t._2, "", null);
                    outputFunction.push(winApiFunction);
                });
    }

    private EventStream<String> createNameBlock() {
        Label name = new Label("Name");
        nameField = new TextField();
        function
                .map(WinApiFunction::getName)
                .feedTo(nameField.textProperty());


        VBox vBox = new VBox(name, nameField);
        getChildren().add(vBox);

        return submit.map(actionEvent -> nameField.getText());
    }

    private EventStream<String> createDescriptionBlock() {
        Label description = new Label("Description");
        descriptionField = new TextField();
        function
                .map(WinApiFunction::getDescription)
                .feedTo(descriptionField.textProperty());

        VBox vBox = new VBox(description, descriptionField);
        getChildren().add(vBox);

        return submit.map(e -> descriptionField.getText());
    }

    private EventStream<List<String>> createFunctionsFields() {
        VBox vBox = new VBox();
        function
                .map(WinApiFunction::getParams)
                .map(f -> f.stream().map(ParamsForm::new).collect(toList()))
                .subscribe(f -> vBox.getChildren().setAll(f));

        return submit.map(e -> vBox.getChildren().stream()
                .map(ParamsForm.class::cast)
                .map(ParamsForm::getParam)
                .collect(toList()));
    }

    public void bindSubmit(EventSource<ActionEvent> submit) {
        submit.subscribe(this.submit::push);
    }

    public EventSource<WinApiFunction> getFunction() {
        return outputFunction;
    }

    static class ParamsForm extends HBox {
        public ParamsForm(String parameter) {
        }

        public String getParam() {
            return null;
        }
    }
}
