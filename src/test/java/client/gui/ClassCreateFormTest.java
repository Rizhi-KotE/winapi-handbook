package client.gui;

import javafx.event.ActionEvent;
import model.WinApiClass;
import model.WinApiFunction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ClassCreateFormTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    private ClassCreateForm bean;
    private WinApiHandbookReactor reactor;
    private FunctionCreateForm functionCreate;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
        bean = context.getBean("classCreateForm", ClassCreateForm.class);
        reactor = context.getBean(WinApiHandbookReactor.class);
        functionCreate = context.getBean(FunctionCreateForm.class);
    }

    @Test
    public void pushClassObject() throws Exception {

        WinApiFunction function1 = new WinApiFunction(1l, "function", "", "", asList("", ""));
        WinApiFunction function = new WinApiFunction(1l, "function", "", "", asList("", ""));
        WinApiClass winApiClass = new WinApiClass(1l, "class1", "", "", asList(function, function1));

        WinApiClass[] winApiClasses = new WinApiClass[1];
        reactor.getClassEventSource().subscribe(winApiClass1 -> winApiClasses[0] = winApiClass1);
        reactor.getClassEventSource().push(winApiClass);


        assertEquals("class1", winApiClasses[0].getName());

        bean.nameField.setText("class2");
        bean.submit.push(new ActionEvent());

        assertEquals("class2", winApiClasses[0].getName());

    }

    @Test
    public void testEditFunction() throws Exception {

    }
}