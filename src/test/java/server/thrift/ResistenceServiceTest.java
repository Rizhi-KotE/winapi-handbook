package server.thrift;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import common.service.impl.WinApiHandbookServiceJdbc;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:serverContext.groovy"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ResistenceServiceTest {
    static {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "winapi_handbook");
    }

    private WinApiHandbookServiceJdbc service;

    @Autowired
    DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;


    @Before
    public void setUp() throws Exception {
        service = new WinApiHandbookServiceJdbc(dataSource);
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_CLASS");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_FUNCTION");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_PARAMETER");
    }

    @Test
    @ExpectedDatabase("classpath:datasets/oneClass.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void testClassCreate() throws Exception {
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec", new ArrayList<>());
        service.saveOrUpdate(winApiClass);
    }

    @Test
    @ExpectedDatabase("classpath:datasets/classWithFunctions.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void createClassWithFunctions() throws Exception {
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec",
                asList(new WinApiFunction(0, "function", "func_dec", new ArrayList<>()),
                        new WinApiFunction(0, "function", "func_dec", new ArrayList<>())));
        service.saveOrUpdate(winApiClass);
    }

    @Test
    @ExpectedDatabase("classpath:datasets/classWithFunctionsAndParameters.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void createClassWithFunctionsAndParameters() throws Exception {
        List<WinApiParameter> params1 =
                asList(new WinApiParameter(0, "type", "param"), new WinApiParameter(0, "type", "param"));
        List<WinApiParameter> params2 =
                asList(new WinApiParameter(0, "type", "param"), new WinApiParameter(0, "type", "param"));
        List<WinApiFunction> functions = asList(new WinApiFunction(0, "function", "func_dec", params1),
                new WinApiFunction(0, "function", "func_dec", params2));
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec",
                functions);
        service.saveOrUpdate(winApiClass);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    public void getWinApiClass() throws Exception {
        WinApiClass clazz = service.getWinApiClass(1);
        assertEquals("class", clazz.getName());
        assertEquals("desc", clazz.getDescription());
        WinApiFunction function = clazz.getFunctions().get(0);
        assertEquals("func", function.getName());
        assertEquals("desc", function.getDescription());
        WinApiParameter param = function.getParams().get(0);
        assertEquals("param", param.getName());
        assertEquals("type", param.getType());
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    public void getClasses() throws Exception {
        List<WinApiClass> list = service.findClasses("cl");
        WinApiClass clazz = list.get(0);
        assertEquals("class", clazz.getName());
        assertEquals("desc", clazz.getDescription());
        WinApiFunction function = clazz.getFunctions().get(0);
        assertEquals("func", function.getName());
        assertEquals("desc", function.getDescription());
        WinApiParameter param = function.getParams().get(0);
        assertEquals("param", param.getName());
        assertEquals("type", param.getType());
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateOneClassFunctionParam.xml")
    public void updateClasses() throws Exception {
        WinApiClass winApiClass = service.getWinApiClass(1);
        winApiClass.setName("class1");
        winApiClass.getFunctions().get(0).setName("func1");
        winApiClass.getFunctions().get(0).getParams().get(0).setName("param1");
        service.updateClass(winApiClass);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/removeClass.xml")
    public void removeClasses() throws Exception {
        service.removeClass(1);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateParameter.xml")
    public void updateParam() throws Exception {
        WinApiParameter winApiParameter = new WinApiParameter(1, "type1", "param1");
        service.updateParam(winApiParameter);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateFunction.xml")
    public void updateFunction() throws Exception {
        WinApiParameter winApiParameter = new WinApiParameter(1, "type1", "param1");
        WinApiFunction winApiFunction = new WinApiFunction(1, "func1", "desc1", asList(winApiParameter));
        service.updateFunction(winApiFunction);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/removeFunction.xml")
    public void removeFunction() throws Exception {
        service.removeWinApiFunction(1);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/removeParameter.xml")
    public void removeParam() throws Exception {
        service.removeWinApiParameter(1);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateClassInsertFunction.xml")
    public void addFunctionToUpdateClass() throws Exception {
        WinApiClass winApiClass = service.getWinApiClass(1);
        WinApiParameter winApiParameter = new WinApiParameter(0, "param3", "param3");
        WinApiFunction function = new WinApiFunction(0, "func2", "desc2", asList(winApiParameter));
        winApiClass.getFunctions().add(function);
        service.saveOrUpdate(winApiClass);
    }
}