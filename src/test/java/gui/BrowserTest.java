package gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;
import service.Topic;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rizhi-kote on 14.03.17.
 */
public class BrowserTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    private Browser browser;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
        browser = context.getBean(Browser.class);
    }

    @Test
    public void changeToBrowseStateShouldBeIdempotentic() throws Exception {
        browser.changeToBrowseState(null);
        browser.changeToBrowseState(null);
    }

    @Test
    public void changeToEditorStateShouldBeIdempotentic() throws Exception {
        browser.changeToEditorState(null);
        browser.changeToEditorState(null);
    }

    @Test
    public void createTopic() throws Exception {
        browser.editor.createNewTopic("newTopic");
        browser.editor.htmlEditor.setHtmlText("content");
        browser.saveTopic(null);
        List<Topic> topics = browser.service.findTopics("newTopic");
        assertEquals("content", topics.get(0).getContent());
    }

    @Test
    public void updateTopic() throws Exception {
        Topic topic = new Topic(1l, "content", "header");
        browser.service.createTopic(topic);
        browser.currentTopic.setValue(topic);
        List<Topic> topics = browser.service.findTopics("");
        assertEquals("content", topics.get(0).getContent());
        browser.editor.htmlEditor.setHtmlText("changed");
        browser.saveTopic(null);
        topics = browser.service.findTopics("");
        assertEquals("changed", topics.get(0).getContent());
    }
}