package gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Setter;
import service.HandbookService;

public class Browser extends Region {

    WebView browser;
    WebEngine webEngine;

    @Setter
    HandbookService service;

    public Browser() {
        //apply the styles
        // load the web page
        //add the web view to the scene

    }

    public void setup() {
        browser  = new WebView();
        webEngine = browser.getEngine();
        getStyleClass().add("browser");
        getChildren().add(browser);
        webEngine.loadContent(service.getTopic(1).getContent());
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}