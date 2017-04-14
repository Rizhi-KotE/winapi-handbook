package server.thrift;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import common.service.WinApiHibernateHandbookService;
import model.Topic;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:serverContext.groovy", "classpath:dbunit.groovy"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@Transactional
public class HibernateHandbookThriftServiceTest {
    @Autowired
    private WinApiHibernateHandbookService service;

    static {
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "winapi_handbook" );
    }

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

//    @Test
//    @DatabaseSetup(value = "datasets/getContentDataSet.xml")
//    public void getContent() throws Exception {
//        String name = service.getWinApiClass(1l).getName();
//        assertEquals("class", name);
//    }
//
//    @Test
//    @DatabaseSetup("classpath:datasets/sampleData.xml")
//    @ExpectedDatabase(value = "classpath:datasets/createTopicExpectedDataSet.xml")
//    public void createTopic() throws Exception {
//        service.createWinApiClass(new WinApiClass(
//                0l, "class", "", "",
//                asList(new WinApiFunction())
//        ));
//    }
//
//    @Test
//    @DatabaseSetup("classpath:datasets/sampleData.xml")
//    @ExpectedDatabase("classpath:datasets/updateTopicExpected.xml")
//    public void updateTopic() throws Exception {
//        WinApiClass winApiClass = service.getWinApiClass(1l);
//        winApiClass.setName("updated");
//        service.updateTopic(winApiClass);
//    }
//
//    @Test
//    @DatabaseSetup("classpath:datasets/sampleData.xml")
//    @ExpectedDatabase("classpath:datasets/removeTopicExpected.xml")
//    public void removeTopic() throws Exception {
//        service.removeTopic(1l);
//    }

    @Test
    @DatabaseSetup("classpath:datasets/empty.xml")
    @ExpectedDatabase("classpath:datasets/oneClass.xml")
    public void testClassCreate() throws Exception {
        WinApiClass winApiClass = new WinApiClass(0, "class", "class_dec",
                asList(new WinApiFunction(0, "function", "func_dec",
                        asList(new WinApiParameter(0, "type", "parameter")))));
        service.createWinApiClass(winApiClass);

    }

//    @Test
//    @DatabaseSetup("classpath:datasets/sampleData.xml")
//    public void findTopicHeaders() throws Exception {
//        List<Topic> headers = service.findTopicsHeaders("");
//        assertEquals(1, headers.size());
//        assertEquals(1l,headers.get(0).getId());
//    }
}