import client.gui.Browser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by rizhi-kote on 17.03.17.
 */
public class StartThriftGui extends Application {
    Browser browser;

    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.setProperty("protocol", "thrift");
        GenericGroovyApplicationContext context =
                new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
        browser = context.getBean(Browser.class);
        stage.setTitle("Web View");
        scene = new Scene(browser, 1030, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }
}
