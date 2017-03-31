package client.gui;

import javafx.event.ActionEvent;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class ClassCreateFormTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    private ClassCreateForm bean;
    private WinApiHandbookReactor reactor;
    private FunctionCreateForm functionCreate;
    private FindClassesWidget find;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
        bean = context.getBean("classCreateForm", ClassCreateForm.class);
        reactor = context.getBean(WinApiHandbookReactor.class);
        functionCreate = context.getBean(FunctionCreateForm.class);
        find = context.getBean(FindClassesWidget.class);
    }

    @Test
    public void pushClassObject() throws Exception {

        WinApiFunction function1 = new WinApiFunction(1l,
                "functionForms",
                "",
                "", emptyList());
        WinApiFunction function = new WinApiFunction(1l, "functionForms", "", "", emptyList());
        WinApiClass winApiClass = new WinApiClass(1l, "class1", "", "", asList(function, function1));

        bean.pushClass(winApiClass);

        assertEquals("class1", bean.getWinApiClass().getName());

        bean.name.setText("class2");
        bean.submit(new ActionEvent());

        assertEquals("class2", bean.getWinApiClass().getName());

    }

    @Test
    public void addNewFunction() throws Exception {

        WinApiClass winApiClass = new WinApiClass(1l, "class1", "", "", new ArrayList<>());

        bean.pushClass(winApiClass);

        assertEquals("class1", bean.getWinApiClass().getName());

        bean.addNewFunction(new ActionEvent());

        assertEquals(1, bean.getClassFunctions().size());
    }

    @Test
    public void testEditFunction() throws Exception {
        FunctionCreateForm form = new FunctionCreateForm();
        form.pushFunction(new WinApiFunction(1l, "name", "desc", "example", new ArrayList<>()));

        assertEquals(1l, form.getFunction().getId());
        assertEquals("name", form.getFunction().getName());
        assertEquals("desc", form.getFunction().getDescription());
// have not implements
//        assertEquals("example", form.getFunction().getExample());
        assertEquals(0, form.getFunction().getParams().size());

        form.id = 2l;
        form.name.setText("name2");
        form.description.setText("desc2");
        form.paramsForms.addAll(new ParamsForm(new WinApiParameter(1l,"","param1")),
                new ParamsForm(new WinApiParameter(1l,"","param2")));

        assertEquals(2, form.getFunction().getId());
        assertEquals("name2", form.getFunction().getName());
        assertEquals("desc2", form.getFunction().getDescription());
        assertEquals(2, form.getFunction().getParams().size());
        assertEquals("param1", form.getFunction().getParams().get(0).getName());
        assertEquals("param2", form.getFunction().getParams().get(1).getName());
    }

    @Test
    public void addNewParam() throws Exception {

        WinApiFunction function = new WinApiFunction(1l, "function", "", "", new ArrayList<>());

        functionCreate.pushFunction(function);

        assertEquals("function", functionCreate.getFunction().getName());

        functionCreate.addNewParam(new ActionEvent());

        assertEquals(1, functionCreate.getParams().size());

        functionCreate.getParamsForm().get(0).pushParameter(new WinApiParameter(1l,"","param2"));

        assertEquals("param", functionCreate.getParams().get(0).getName());
    }

    @Test
    public void classesList() throws Exception {

        List<WinApiClass> winApiClasses = asList(
                new WinApiClass(1l, "class1", "", "", new ArrayList<>()),
                new WinApiClass(2l, "class2", "", "", new ArrayList<>()));


        find.pushClasses(winApiClasses);

        assertEquals("class1", find.getClasses().get(0).getName());
        assertEquals("class2", find.getClasses().get(1).getName());

    }

    @Test
    public void chooseClass() throws Exception {

        List<WinApiClass> winApiClasses = asList(
                new WinApiClass(1l, "class1", "", "", new ArrayList<>()),
                new WinApiClass(2l, "class2", "", "", new ArrayList<>()));


        find.pushClasses(winApiClasses);

        find.chooseClass(winApiClasses.get(0));

        assertEquals("class1", bean.getWinApiClass().getName());

    }
}