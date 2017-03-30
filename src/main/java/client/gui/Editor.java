package client.gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import lombok.Setter;
import common.service.WinApiHandbookService;

public class Editor extends VBox {

//    @Setter
//    ObjectProperty<WinApiClass> currentTopic;
//    @Setter
//    WinApiHandbookService service;
//
//    HTMLEditor htmlEditor;
//
//    void setup() {
//        htmlEditor = new HTMLEditor();
//        getChildren().addAll(htmlEditor);
//        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.S, new KeyCombination.Modifier[]{KeyCombination.CONTROL_DOWN});
//        currentTopic.addListener(this::changeCurrentTopic);
//    }
//
//    void changeCurrentTopic(ObservableValue<? extends WinApiClass> observable, WinApiClass oldValue, WinApiClass newValue) {
//        if (newValue != null) {
//            htmlEditor.setHtmlText(newValue.getContent());
//        }
//    }
//
//    public void saveTopic() {
//        WinApiClass value = currentTopic.getValue();
//        if(value.getId()==0) return;
//        WinApiClass topic = new WinApiClass(value.getId(), value.getContent(), value.getHeader());
//        topic.setContent(htmlEditor.getHtmlText());
//        service.updateTopic(topic);
//        currentTopic.setValue(topic);
//    }
//
//    public void createNewTopic(String topicName) {
//        saveTopic();
//        WinApiClass topic = new WinApiClass(0l, "", topicName);
//        long id = service.createTopic(topic);
//        topic.setId(id);
//        currentTopic.setValue(topic);
//    }
}
