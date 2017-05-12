package client.gui;

import javafx.event.ActionEvent;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import model.WinApiUserElement;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ClassCreateFormTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    private ElementCreateForm bean;
    private WinApiHandbookReactor reactor;
    private FunctionCreateForm functionCreate;
    private FindClassesWidget find;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("ClassCreateFormTestContext.groovy"));
        bean = context.getBean("classCreateForm", ElementCreateForm.class);
        reactor = context.getBean(WinApiHandbookReactor.class);
        functionCreate = context.getBean(FunctionCreateForm.class);
        find = context.getBean(FindClassesWidget.class);
    }

    @Test
    public void pushClassObject() throws Exception {

        WinApiFunction function = new WinApiFunction(1,
                "func",
                "dec",
                "syntax",
                new ArrayList<>(),
                "return_type",
                "return_dec",
                new ArrayList<>());
        WinApiFunction function1 = new WinApiFunction(2,
                "func",
                "dec",
                "syntax",
                new ArrayList<>(),
                "return_type",
                "return_dec",
                new ArrayList<>());
        WinApiUserElement winApiUserElement = new WinApiUserElement(1, "element", "", asList(function, function1));

        bean.pushClass(winApiUserElement);

        assertEquals("element", bean.getWinApiClass().getName());

        bean.name.setText("element2");
        bean.submit(new ActionEvent());

        assertEquals("element2", bean.getWinApiClass().getName());
        assertEquals(1, bean.getWinApiClass().getId());

        WinApiFunction winApiFunction = bean.getWinApiClass().getFunctions().get(0);

        assertEquals(1, winApiFunction.getId());
        winApiFunction = bean.getWinApiClass().getFunctions().get(1);
        assertEquals(2, winApiFunction.getId());


    }

    @Test
    public void addNewFunction() throws Exception {

        WinApiUserElement winApiUserElement = new WinApiUserElement(1, "element", "", new ArrayList<>());

        bean.pushClass(winApiUserElement);

        assertEquals("element", bean.getWinApiClass().getName());

        bean.addNewFunction(new ActionEvent());

        assertEquals(1, bean.getClassFunctions().size());
    }

    @Test
    public void testEditFunction() throws Exception {
        FunctionCreateForm form = new FunctionCreateForm(null);
        form.pushFunction(new WinApiFunction(1,
                "func",
                "dec",
                "syntax",
                new ArrayList<>(),
                "return_type",
                "return_dec",
                new ArrayList<>()));

        assertEquals(1, form.getFunction().getId());
        assertEquals("func", form.getFunction().getName());
        assertEquals("dec", form.getFunction().getDescription());
        assertEquals("syntax", form.getFunction().getSyntax());
        assertEquals("return_type", form.getFunction().getReturnType());
        assertEquals("return_dec", form.getFunction().getReturnTypeDescription());
        assertEquals(0, form.getFunction().getParams().size());
        assertEquals(0, form.getFunction().getRequirements().size());

        form.id = 2;
        form.name.setText("func2");
        form.description.setText("dec2");
        form.paramsForms.addAll(new ParamsForm(new WinApiParameter(0, "first_def", "type_def", "dec")),
                new ParamsForm(new WinApiParameter(0, "first_def", "type_def", "dec")));
        form.requirementForms.addAll(new RequirementForm(new WinApiFunctionRequirement(0, "key", "value")),
                new RequirementForm(new WinApiFunctionRequirement(0, "key", "value")));

        assertEquals(2, form.getFunction().getId());
        assertEquals("func2", form.getFunction().getName());
        assertEquals("dec2", form.getFunction().getDescription());
        assertEquals(2, form.getFunction().getParams().size());
        assertEquals("dec", form.getFunction().getParams().get(0).getDescription());
        assertEquals("first_def", form.getFunction().getParams().get(0).getFirstDefinition());
        assertEquals("type_def", form.getFunction().getParams().get(0).getTypeDefinition());
        assertEquals(2, form.getFunction().getRequirements().size());
        assertEquals("key", form.getFunction().getRequirements().get(0).getKey());
        assertEquals("value", form.getFunction().getRequirements().get(0).getValue());
    }

    @Test
    public void addNewParam() throws Exception {

        WinApiFunction function = new WinApiFunction(1,
                "func",
                "dec",
                "syntax",
                new ArrayList<>(),
                "return_type",
                "return_dec",
                new ArrayList<>());

        functionCreate.pushFunction(function);
        assertEquals("func", functionCreate.getFunction().getName());
        functionCreate.addNewParam(new ActionEvent());

        assertEquals(1, functionCreate.getParams().size());
        ((ParamsForm) functionCreate.paramsForms
                .get(0))
                .pushParameter(new WinApiParameter(0, "first_def", "type_def", "dec"));
        assertEquals("type_def", functionCreate.getParams().get(0).getTypeDefinition());
        assertEquals("first_def", functionCreate.getParams().get(0).getFirstDefinition());
        assertEquals("dec", functionCreate.getParams().get(0).getDescription());

        functionCreate.addNewRequirement(new ActionEvent());

        assertEquals(1, functionCreate.getRequirements().size());
        ((ParamsForm) functionCreate.paramsForms
                .get(0))
                .pushParameter(new WinApiParameter(0, "first_def", "type_def", "dec"));
        assertEquals("type_def", functionCreate.getParams().get(0).getTypeDefinition());
        assertEquals("first_def", functionCreate.getParams().get(0).getFirstDefinition());
        assertEquals("dec", functionCreate.getParams().get(0).getDescription());
    }

    @Test
    public void classesList() throws Exception {

        List<WinApiUserElement> winApiUserElements = asList(
                new WinApiUserElement(1l, "class1", "", new ArrayList<>()),
                new WinApiUserElement(2l, "class2", "", new ArrayList<>()));


        find.pushClasses(winApiUserElements);

        assertEquals("class1", find.getClasses().get(0).getName());
        assertEquals("class2", find.getClasses().get(1).getName());

    }

    @Test
    public void chooseClass() throws Exception {

        List<WinApiUserElement> winApiUserElements = asList(
                new WinApiUserElement(1l, "class1", "", new ArrayList<>()),
                new WinApiUserElement(2l, "class2", "", new ArrayList<>()));


        find.pushClasses(winApiUserElements);

        find.chooseClass(winApiUserElements.get(0));

        assertEquals("class1", bean.getWinApiClass().getName());

    }
}