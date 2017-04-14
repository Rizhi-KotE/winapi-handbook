package server.thrift;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import common.service.WinApiHandbookService;
import common.service.impl.WinApiHandbookServiceJdbc;
import model.WinApiClass;
import model.WinApiFunction;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;

import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:serverContext.groovy", "classpath:dbunit.groovy"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class HibernateHandbookThriftServiceTest {
    static {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "winapi_handbook");
    }

    @Autowired
    private WinApiHandbookServiceJdbc service;
    @Autowired
    private ApplicationContext applicationContext;


    @After
    public void setUp() throws Exception {

//                // database connection
//                Class driverClass = Class.forName("com.mysql.jdbc.Driver");
//                Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/winapi_handbook?useUnicode=true&characterEncoding=utf8", "root", "1");
//                IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//
//                // write DTD file
//        FileOutputStream out = new FileOutputStream("test.dtd");
//        FlatDtdDataSet.write(connection.createDataSet(), out);

        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_CLASS");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_FUNCTION");
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "WINAPI_PARAMETER");
    }

    @Test
    @DatabaseSetup("classpath:datasets/empty.xml")
    @ExpectedDatabase("classpath:datasets/oneClass.xml")
    public void testClassCreate() throws Exception {
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec", new ArrayList<>());
        service.createWinApiClass(winApiClass);
    }

    @Test
    @DatabaseSetup("classpath:datasets/empty.xml")
    @ExpectedDatabase("classpath:datasets/classWithFunctions.xml")
    public void createClassWithFunctions() throws Exception {
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec",
                asList(new WinApiFunction(0, "function", "func_dec", new ArrayList<>()),
                        new WinApiFunction(0, "function", "func_dec", new ArrayList<>())));
        service.createWinApiClass(winApiClass);
    }
}