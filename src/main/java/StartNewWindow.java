import client.gui.ClassCreateForm;
import client.gui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class StartNewWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        System.setProperty("protocol", "thrift");
        GenericGroovyApplicationContext context =
                new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
        MainWindow bean = context.getBean(MainWindow.class);
        stage.setTitle("Web View");
        Scene scene = new Scene(bean, 1030, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }
}
