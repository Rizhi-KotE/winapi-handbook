package gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import lombok.Setter;
import service.Topic;

public class Editor extends VBox {

    @Setter
    ObjectProperty<Topic> currentTopic;
    private HTMLEditor htmlEditor;

    void setup() {
        htmlEditor = new HTMLEditor();
        getChildren().addAll(htmlEditor);
        currentTopic.addListener(this::changeCurrentTopic);
    }

    void changeCurrentTopic(ObservableValue<? extends Topic> observable, Topic oldValue, Topic newValue) {
        if (newValue != null)
            htmlEditor.setHtmlText(newValue.getContent());
    }
}
