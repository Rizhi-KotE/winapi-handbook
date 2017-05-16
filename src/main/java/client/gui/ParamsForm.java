package client.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WinApiParameter;
import org.reactfx.EventSource;

class ParamsForm extends VBox {
    final EventSource<WinApiParameter> parameter;
    TextField firstDefinition;
    long id;
    long functionId;
    long elementId;
    private Runnable o;
    private TextArea description;
    private TextField typeDefinition;

    ParamsForm(WinApiParameter parameter) {

        this.parameter = new EventSource<>();
        this.parameter
                .map(WinApiParameter::getId)
                .subscribe(e -> id = e);
        this.parameter
                .map(WinApiParameter::getElementId)
                .subscribe(e -> elementId = e);
        this.parameter
                .map(WinApiParameter::getFunctionId)
                .subscribe(e -> functionId = e);
        Button remove = new Button("Remove");
        remove.setOnAction(e -> o.run());
        setSpacing(10);
        createFirstDefinitionBlock();
        createTypeDefinitionBlock();
        createDescriptionBlock();
        getChildren().add(remove);
        pushParameter(parameter);
    }

    private void createTypeDefinitionBlock() {
        Label label = new Label("type");
        typeDefinition = new TextField();
        parameter
                .map(WinApiParameter::getTypeDefinition)
                .feedTo(typeDefinition.textProperty());
        VBox box = new VBox(label, typeDefinition);
        setSpacing(10);
        getChildren().addAll(box);
    }

    private void createDescriptionBlock() {
        Label label = new Label("description");
        description = new TextArea();
        description.setWrapText(true);
        parameter
                .map(WinApiParameter::getDescription)
                .feedTo(description.textProperty());
        setSpacing(10);
        VBox box = new VBox(label, description);
        getChildren().addAll(box);
    }

    private void createFirstDefinitionBlock() {
        Label label = new Label("definition");
        firstDefinition = new TextField();
        parameter
                .map(WinApiParameter::getFirstDefinition)
                .feedTo(firstDefinition.textProperty());
        VBox box = new VBox(label, firstDefinition);
        getChildren().addAll(box);
    }

    void pushParameter(WinApiParameter parameter) {
        this.parameter.push(parameter);
    }

    WinApiParameter getFirstDefinition() {
        return new WinApiParameter(id, functionId, elementId, firstDefinition.getText(), typeDefinition.getText(), description.getText());
    }

    public void removeAction(Runnable o) {
        this.o = o;
    }
}
