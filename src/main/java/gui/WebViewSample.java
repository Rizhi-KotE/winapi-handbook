package gui;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;


public class WebViewSample extends Application {
    Browser browser;

    private Scene scene;

    @Override
    public void start(Stage stage) {
        GenericGroovyApplicationContext context =
                new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
        browser = context.getBean(Browser.class);
        stage.setTitle("Web View");
        scene = new Scene(browser, 1030, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
