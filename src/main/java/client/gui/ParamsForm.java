package client.gui;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.WinApiParameter;
import org.reactfx.EventSource;

class ParamsForm extends HBox {
    final EventSource<WinApiParameter> parameter;
    TextField param;

    ParamsForm(WinApiParameter parameter) {

        this.parameter = new EventSource<>();
        crateForm();
        pushParameter(parameter);
    }

    private void crateForm() {
        param = new TextField();
        parameter
                .map(WinApiParameter::getName)
                .feedTo(param.textProperty());
        getChildren().add(param);
    }

    void pushParameter(WinApiParameter parameter) {
        this.parameter.push(parameter);
    }

    WinApiParameter getParam() {
        return new WinApiParameter(null, "", param.getText());
    }
}
