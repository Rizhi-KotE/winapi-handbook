package gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;
import service.Topic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditorTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    private Editor editor;

    @Before
    public void setUp() throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
        editor = context.getBean(Editor.class);
    }

    @Test
    public void saveTopic() throws Exception {
        editor.htmlEditor.setHtmlText("aqwert");
        editor.saveTopic();
        assertEquals("aqwert", editor.currentTopic.getValue().getContent());
    }

    @Test
    public void currentTopicShouldBeNotNull() throws Exception {
        assertNotNull(editor.currentTopic);
        assertNotNull(editor.currentTopic.getValue());
    }

    @Test
    public void createNewTopic() throws Exception {
        editor.createNewTopic("header");

    }

    @Test
    public void changeTopicToNull() {
        editor.currentTopic.setValue(null);
        assertEquals("<html><head></head><body contenteditable=\"true\"></body></html>", editor.htmlEditor.getHtmlText());
    }

    @Test
    public void changeTopicToNotNull() {
        Topic topic = new Topic(1, "content", "header");
        editor.currentTopic.setValue(topic);
        assertEquals(topic.getContent(), editor.htmlEditor.getHtmlText());
    }

}