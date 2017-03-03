package gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Setter;
import service.HandbookService;
import service.Topic;

import java.util.Optional;

public class Browser extends VBox {

    @Setter
    Editor editor;
    @Setter
    ObjectProperty<Topic> currentTopic;
    WebView browser;
    WebEngine webEngine;
    @Setter
    HandbookService service;
    @Setter
    FindTopicsWidget find;
    private HBox hBox;

    public void setup() {
        browser = new WebView();
        webEngine = browser.getEngine();
        getStyleClass().add("browser");
        hBox = new HBox(find);
        getChildren().addAll(createMenuBar(), hBox);
        currentTopic.addListener(this::topicChanged);
        changeToBrowseState(null);
    }

    private void changeToBrowseState(Event e) {
        hBox.getChildren().remove(editor);
        hBox.getChildren().add(0, browser);
    }

    private void changeToEditorState(Event e) {
        hBox.getChildren().remove(browser);
        hBox.getChildren().add(0, editor);
    }

    public HBox createMenuBar() {
        Button browse = new Button("browse");
        Button edit = new Button("edit");
        browse.setOnAction(this::changeToBrowseState);
        edit.setOnAction(this::changeToEditorState);
        Button create = new Button("create");
        create.setOnAction(this::createNewTopic);
        Button save = new Button("save");
        save.setOnAction(this::saveTopic);
        return new HBox(browse, edit, create, save);
    }

    private void saveTopic(ActionEvent actionEvent) {
        editor.saveTopic();
    }

    private void createNewTopic(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("New topic");
        dialog.setHeaderText("Create new topic");
        dialog.setContentText("Please enter topic name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            editor.createNewTopic(name);
            changeToEditorState(actionEvent);
        });
    }


    public void topicChanged(ObservableValue<? extends Topic> observable,
                             Topic oldValue, Topic newValue) {
        if (newValue != null)
            webEngine.loadContent(newValue.getContent());
    }
}