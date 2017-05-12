package client.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import org.reactfx.EventSource;

class RequirementForm extends VBox {
    final EventSource<WinApiFunctionRequirement> parameter;
    TextField key;
    long id;
    private TextField value;
    private TextField typeDefinition;
    private Runnable o;

    RequirementForm(WinApiFunctionRequirement requirement) {

        this.parameter = new EventSource<>();
        this.parameter
                .map(WinApiFunctionRequirement::getId)
                .subscribe(e -> id = e);
        crateForm();
        pushRequirement(requirement);
    }

    private void crateForm() {

        key = new TextField();
        key.setPrefWidth(550);
        value = new TextField();
        value.setPrefWidth(550);
        parameter
                .map(WinApiFunctionRequirement::getKey)
                .feedTo(key.textProperty());
        parameter
                .map(WinApiFunctionRequirement::getValue)
                .feedTo(value.textProperty());
        Button remove = new Button("Remove");
        remove.setOnAction(e -> o.run());
        setSpacing(10);
        HBox hBox = new HBox(key, value);
        getChildren().addAll(hBox, remove);
    }

    void pushRequirement(WinApiFunctionRequirement requirement) {
        this.parameter.push(requirement);
    }

    WinApiParameter getKey() {
        return new WinApiParameter(id, value.getText(), typeDefinition.getText(), key.getText());
    }

    public WinApiFunctionRequirement getRequirement() {
        return new WinApiFunctionRequirement(id, key.getText(), value.getText());
    }

    public void removeAction(Runnable o){

        this.o = o;
    }
}
