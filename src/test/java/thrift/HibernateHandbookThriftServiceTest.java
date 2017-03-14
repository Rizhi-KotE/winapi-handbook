package thrift;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import model.Topic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:serverContext.groovy")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@Transactional
public class HibernateHandbookThriftServiceTest {
    @Autowired
    private HibernateHandbookThriftService service;

    @Autowired
    private ApplicationContext applicationContext;


    @After
    public void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "topics");
    }

    @Test
    @DatabaseSetup(value = "classpath:datasets/getContentDataSet.xml")
    public void getContent() throws Exception {
        String content = service.getTopic(1l).getContent();
        assertEquals("content", content);
    }

    @Test
    @DatabaseSetup("classpath:datasets/sampleData.xml")
    @ExpectedDatabase(value = "classpath:datasets/createTopicExpectedDataSet.xml")
    public void createTopic() throws Exception {
        service.createTopic(new Topic(0, "content","header"));
    }

    @Test
    @DatabaseSetup("classpath:datasets/sampleData.xml")
    @ExpectedDatabase("classpath:datasets/updateTopicExpected.xml")
    public void updateTopic() throws Exception {
        service.updateTopic(new Topic(1l, "updated", "header"));
    }

    @Test
    @DatabaseSetup("classpath:datasets/sampleData.xml")
    @ExpectedDatabase("classpath:datasets/updateNonexistTopicExpected.xml")
    public void updateNonexistTopic() throws Exception {
        service.updateTopic(new Topic(2l, "updated", ""));
    }

    @Test
    @DatabaseSetup("classpath:datasets/sampleData.xml")
    @ExpectedDatabase("classpath:datasets/removeTopicExpected.xml")
    public void removeTopic() throws Exception {
        service.removeTopic(1l);
    }

    @Test
    @DatabaseSetup("classpath:datasets/sampleData.xml")
    public void findTopicHeaders() throws Exception {
        List<Topic> headers = service.findTopicsHeaders("");
        assertEquals(1, headers.size());
        assertEquals(1l,headers.get(0).getId());
    }
}