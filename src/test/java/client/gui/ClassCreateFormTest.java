package client.gui;

import javafx.event.ActionEvent;
import model.WinApiUserElement;
import model.WinApiFunction;
import model.WinApiParameter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class ClassCreateFormTest {
//    @Rule
//    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
//    private ClassCreateForm bean;
//    private WinApiHandbookReactor reactor;
//    private FunctionCreateForm functionCreate;
//    private FindClassesWidget find;
//
//    @Before
//    public void setUp() throws Exception {
//        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
//        bean = context.getBean("classCreateForm", ClassCreateForm.class);
//        reactor = context.getBean(WinApiHandbookReactor.class);
//        functionCreate = context.getBean(FunctionCreateForm.class);
//        find = context.getBean(FindClassesWidget.class);
//    }
//
//    @Test
//    public void pushClassObject() throws Exception {
//
//        WinApiFunction function = new WinApiFunction(1,
//                "functionForms",
//
//                "", emptyList());
//        WinApiFunction function1 = new WinApiFunction(2, "functionForms", "",  emptyList());
//        WinApiUserElement winApiUserElement = new WinApiUserElement(1, "class1", "", asList(function, function1));
//
//        bean.pushClass(winApiUserElement);
//
//        assertEquals("class1", bean.getWinApiClass().getName());
//
//        bean.name.setText("class2");
//        bean.submit(new ActionEvent());
//
//        assertEquals("class2", bean.getWinApiClass().getName());
//        assertEquals(1, bean.getWinApiClass().getId());
//
//        WinApiFunction winApiFunction = bean.getWinApiClass().getFunctions().get(0);
//
//        assertEquals(1, winApiFunction.getId());
//        winApiFunction = bean.getWinApiClass().getFunctions().get(1);
//        assertEquals(2, winApiFunction.getId());
//
//
//    }
//
//    @Test
//    public void addNewFunction() throws Exception {
//
//        WinApiUserElement winApiUserElement = new WinApiUserElement(1l, "class1", "",  new ArrayList<>());
//
//        bean.pushClass(winApiUserElement);
//
//        assertEquals("class1", bean.getWinApiClass().getName());
//
//        bean.addNewFunction(new ActionEvent());
//
//        assertEquals(1, bean.getClassFunctions().size());
//    }
//
//    @Test
//    public void testEditFunction() throws Exception {
//        FunctionCreateForm form = new FunctionCreateForm(null);
//        form.pushFunction(new WinApiFunction(1l, "name", "desc",  new ArrayList<>()));
//
//        assertEquals(1l, form.getFunction().getId());
//        assertEquals("name", form.getFunction().getName());
//        assertEquals("desc", form.getFunction().getDescription());
//// have not implements
////        assertEquals("example", form.getFunction().getExample());
//        assertEquals(0, form.getFunction().getParams().size());
//
//        form.id = 2l;
//        form.name.setText("name2");
//        form.description.setText("desc2");
//        form.paramsForms.addAll(new ParamsForm(new WinApiParameter(1, "", "param1")),
//                new ParamsForm(new WinApiParameter(2, "", "param2")));
//
//        assertEquals(2, form.getFunction().getId());
//        assertEquals("name2", form.getFunction().getName());
//        assertEquals("desc2", form.getFunction().getDescription());
//        assertEquals(2, form.getFunction().getParams().size());
//        assertEquals("param1", form.getFunction().getParams().get(0).getName());
//        assertEquals(1, form.getFunction().getParams().get(0).getId());
//        assertEquals("param2", form.getFunction().getParams().get(1).getName());
//        assertEquals(2, form.getFunction().getParams().get(1).getId());
//    }
//
//    @Test
//    public void addNewParam() throws Exception {
//
//        WinApiFunction function = new WinApiFunction(1, "function", "", new ArrayList<>());
//
//        functionCreate.pushFunction(function);
//
//        assertEquals("function", functionCreate.getFunction().getName());
//
//        functionCreate.addNewParam(new ActionEvent());
//
//        assertEquals(1, functionCreate.getParams().size());
//
//        ((ParamsForm) functionCreate.paramsForms
//                .get(0))
//                .pushParameter(new WinApiParameter(1, "", "param2"));
//
//        assertEquals("param2", functionCreate.getParams().get(0).getName());
//    }
//
//    @Test
//    public void classesList() throws Exception {
//
//        List<WinApiUserElement> winApiUserElements = asList(
//                new WinApiUserElement(1l, "class1", "",  new ArrayList<>()),
//                new WinApiUserElement(2l, "class2", "",  new ArrayList<>()));
//
//
//        find.pushClasses(winApiUserElements);
//
//        assertEquals("class1", find.getClasses().get(0).getName());
//        assertEquals("class2", find.getClasses().get(1).getName());
//
//    }
//
//    @Test
//    public void chooseClass() throws Exception {
//
//        List<WinApiUserElement> winApiUserElements = asList(
//                new WinApiUserElement(1l, "class1", "",  new ArrayList<>()),
//                new WinApiUserElement(2l, "class2", "",  new ArrayList<>()));
//
//
//        find.pushClasses(winApiUserElements);
//
//        find.chooseClass(winApiUserElements.get(0));
//
//        assertEquals("class1", bean.getWinApiClass().getName());
//
//    }
}