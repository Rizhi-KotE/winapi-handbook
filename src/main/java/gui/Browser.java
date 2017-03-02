package gui;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Setter;
import service.HandbookService;
import service.Topic;

public class Browser extends HBox {

    WebView browser;
    WebEngine webEngine;

    @Setter
    HandbookService service;

    @Setter
    FindTopicsWidget find;

    public Browser() {
        //apply the styles
        // load the web page
        //add the web view to the scene

    }

    public void setup() {
        browser = new WebView();
        webEngine = browser.getEngine();
        getStyleClass().add("browser");
        getChildren().addAll(browser, find);
        webEngine.loadContent(service.getTopic(1).getContent());
        find.bindSelectionListener(this::topicChanged);
    }

    public void topicChanged(ObservableValue<? extends Topic> observable,
                             Topic oldValue, Topic newValue) {
        if (newValue != null)
            webEngine.loadContent(service.getTopic(newValue.getId()).getContent());
    }
}