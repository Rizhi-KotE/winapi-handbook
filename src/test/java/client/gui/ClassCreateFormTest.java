package client.gui;

import io.reactivex.subscribers.TestSubscriber;
import javafx.event.ActionEvent;
import model.WinApiClass;
import model.WinApiFunction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

public class ClassCreateFormTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    private ClassCreateForm bean;
    private WinApiHandbookReactor reactor;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
        bean = context.getBean(ClassCreateForm.class);
        reactor = context.getBean(WinApiHandbookReactor.class);
    }

    @Test
    public void pushClassObject() throws Exception {

        WinApiFunction function1 = new WinApiFunction(1l, "function", "", "", asList("", ""));
        WinApiFunction function = new WinApiFunction(1l, "function", "", "", asList("", ""));
        WinApiClass winApiClass = new WinApiClass(1l, "", "", "", asList(function, function1));

        reactor.getClassEventSource().push(winApiClass);

        TestSubscriber<WinApiClass> testSubscriber = new TestSubscriber<>();
        bean.submit.push(new ActionEvent());
        reactor.getClassEventSource().
reactor.getClassEventSource().subscribe(testSubscriber);
        testSubscriber.assertResult(winApiClass);
    }
}