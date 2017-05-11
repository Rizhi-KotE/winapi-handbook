package server.thrift;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import common.service.impl.WinApiHandbookServiceJdbc;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import model.WinApiUserElement;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;
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
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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

    @Autowired
    DataSource dataSource;
    private WinApiHandbookServiceJdbc service;
    @Autowired
    private ApplicationContext applicationContext;


    @Before
    public void setUp() throws Exception {
        service = new WinApiHandbookServiceJdbc(dataSource);
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_USER_ELEMENT");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_FUNCTION");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_PARAMETER");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "REQUIREMET");

//        Connection jdbcConnection = dataSource.getConnection();
//        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//
//        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));
    }

    @Test
    @ExpectedDatabase("classpath:datasets/oneElement.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void testClassCreate() throws Exception {
        WinApiUserElement winApiUserElement = new WinApiUserElement(0, "element", "element_dec", new ArrayList<>());
        service.saveOrUpdateUserElement(winApiUserElement);
    }

    @Test
    @ExpectedDatabase("classpath:datasets/elementWithFunctions.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void createClassWithFunctions() throws Exception {
        WinApiUserElement winApiUserElement = new WinApiUserElement(0, "class", "class_dec",
                asList(new WinApiFunction(0,
                                "func",
                                "dec",
                                "syntax",
                                new ArrayList<>(),
                                "return_type",
                                "return_dec",
                                new ArrayList<>()),
                        new WinApiFunction(0,
                                "func",
                                "dec",
                                "syntax",
                                new ArrayList<>(),
                                "return_type",
                                "return_dec",
                                new ArrayList<>())));
        service.saveOrUpdateUserElement(winApiUserElement);
    }

    @Test
    @ExpectedDatabase("classpath:datasets/elementWithFunctionsAndParameters.xml")
    @DatabaseSetup("classpath:datasets/empty.xml")
    public void createClassWithFunctionsAndParameters() throws Exception {
        List<WinApiParameter> params1 =
                asList(new WinApiParameter(0, "first_def", "type_def", "dec"),
                        new WinApiParameter(0, "first_def", "type_def", "dec"));
        List<WinApiParameter> params2 =
                asList(new WinApiParameter(0, "first_def", "type_def", "dec"),
                        new WinApiParameter(0, "first_def", "type_def", "dec"));
        List<WinApiFunctionRequirement> requirements = asList(new WinApiFunctionRequirement(0,"key","value"));
        List<WinApiFunction> functions = asList(new WinApiFunction(0,
                        "func",
                        "dec",
                        "syntax",
                        params1,
                        "return_type",
                        "return_dec",
                        requirements),
                new WinApiFunction(0,
                        "func",
                        "dec",
                        "syntax",
                        params2,
                        "return_type",
                        "return_dec",
                        new ArrayList<>()));
        WinApiUserElement winApiUserElement = new WinApiUserElement(0, "elem", "dec",
                functions);
        service.saveOrUpdateUserElement(winApiUserElement);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    public void getWinApiClass() throws Exception {
        WinApiUserElement element = service.getUserElement(1);
        assertEquals("elem", element.getName());
        assertEquals("dec", element.getDescription());
        WinApiFunction function = element.getFunctions().get(0);
        assertEquals("func", function.getName());
        assertEquals("dec", function.getDescription());
        assertEquals("return_type", function.getReturnType());
        assertEquals("return_dec", function.getReturnTypeDescription());
        assertEquals("syntax", function.getSyntax());
        WinApiParameter param = function.getParams().get(0);
        assertEquals("first_def", param.getFirstDefinition());
        assertEquals("type_def", param.getTypeDefinition());
        assertEquals("dec", param.getDescription());
        WinApiFunctionRequirement requirement = function.getRequirements().get(0);
        assertEquals("key", requirement.getKey());
        assertEquals("value", requirement.getValue());
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateOneClassFunctionParam.xml")
    public void updateClasses() throws Exception {
        WinApiUserElement winApiUserElement = service.getUserElement(1);
        winApiUserElement.setName("elem1");
        winApiUserElement.getFunctions().get(0).setName("func1");
        winApiUserElement.getFunctions().get(0).getRequirements().get(0).setKey("key1");
        winApiUserElement.getFunctions().get(0).getParams().get(0).setDescription("dec1");
        service.updateClass(winApiUserElement);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/removeUserElement.xml")
    public void removeClasses() throws Exception {
        service.removeElement(1);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/removeRequirement.xml")
    public void removeRequirement() throws Exception {
        service.removeRequirement(1);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateParameter.xml")
    public void updateParam() throws Exception {
        WinApiParameter winApiParameter = new WinApiParameter(1, "first_def1", "type_def1", "dec1");
        service.updateParam(winApiParameter);
    }

    @Test
    @DatabaseSetup("classpath:datasets/oneClassFunctionParam.xml")
    @ExpectedDatabase("classpath:datasets/updateFunction.xml")
    public void updateFunction() throws Exception {
        List<WinApiParameter> params = asList(new WinApiParameter(1, "first_def", "type_def", "dec1"));
        List<WinApiFunctionRequirement> requirements = asList(new WinApiFunctionRequirement(1, "key1", "value"));
        WinApiFunction winApiFunction = new WinApiFunction(1,
                "func1",
                "dec",
                "syntax",
                params,
                "return_type",
                "return_dec",
                requirements);
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
        WinApiUserElement winApiUserElement = service.getUserElement(1);
        List<WinApiParameter> params = asList(new WinApiParameter(0, "first_def3", "type_def3", "dec3"));
        List<WinApiFunctionRequirement> requirements = asList(new WinApiFunctionRequirement(0, "key", "value"));
        WinApiFunction function = new WinApiFunction(0,
                "func2",
                "dec2",
                "syntax2",
                params,
                "return_type2",
                "return_dec2",
                requirements);
        winApiUserElement.getFunctions().add(function);
        service.saveOrUpdateUserElement(winApiUserElement);
    }
}