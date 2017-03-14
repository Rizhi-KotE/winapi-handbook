package gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import lombok.Setter;
import service.HandbookService;
import service.Topic;

public class Editor extends VBox {

    @Setter
    ObjectProperty<Topic> currentTopic;
    @Setter
    HandbookService service;

    HTMLEditor htmlEditor;

    void setup() {
        htmlEditor = new HTMLEditor();
        getChildren().addAll(htmlEditor);
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.S, new KeyCombination.Modifier[]{KeyCombination.CONTROL_DOWN});
        currentTopic.addListener(this::changeCurrentTopic);
    }

    void changeCurrentTopic(ObservableValue<? extends Topic> observable, Topic oldValue, Topic newValue) {
        if (newValue != null) {
            htmlEditor.setHtmlText(newValue.getContent());
        }
    }

    public void saveTopic() {
        Topic value = currentTopic.getValue();
        if(value.getId()==0) return;
        Topic topic = new Topic(value.getId(), value.getContent(), value.getHeader());
        topic.setContent(htmlEditor.getHtmlText());
        service.updateTopic(topic);
        currentTopic.setValue(topic);
    }

    public void createNewTopic(String topicName) {
        saveTopic();
        Topic topic = new Topic(0l, "", topicName);
        long id = service.createTopic(topic);
        topic.setId(id);
        currentTopic.setValue(topic);
    }
}
