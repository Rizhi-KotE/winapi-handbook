package client.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditorTest {

//    @Rule
//    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
//
//    private Editor editor;
//
//    @Before
//    public void setUp() throws Exception {
//        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
//        editor = context.getBean(Editor.class);
//    }
//
//    @Test
//    public void currentTopicShouldBeNotNull() throws Exception {
//        assertNotNull(editor.currentTopic);
//        assertNotNull(editor.currentTopic.getValue());
//    }
//
//    @Test
//    public void createNewTopic() throws Exception {
//        editor.createNewTopic("header");
//        editor.htmlEditor.setHtmlText("content2");
//        editor.saveTopic();
//        List<WinApiUserElement> header = editor.service.findClasses("header");
//        assertEquals("content2", header.get(0).getContent());
//    }
//
//    @Test
//    public void changeTopic() throws Exception {
//        WinApiUserElement topic = new WinApiUserElement(1l, "content", "header");
//        editor.service.saveOrUpdateUserElement(topic);
//        editor.currentTopic.setValue(topic);
//        editor.htmlEditor.setHtmlText("changed");
//        editor.saveTopic();
//        List<WinApiUserElement> header = editor.service.findClasses("header");
//        assertEquals("changed", header.get(0).getContent());
//    }
//
//    @Test
//    public void changeTopicToNull() {
//        editor.currentTopic.setValue(null);
//        assertEquals("<html><head></head><body contenteditable=\"true\"></body></html>", editor.htmlEditor.getHtmlText());
//    }
//
//    @Test
//    public void changeTopicToNotNull() {
//        WinApiUserElement topic = new WinApiUserElement(1, "content", "header");
//        editor.currentTopic.setValue(topic);
//        assertEquals(topic.getContent(), editor.htmlEditor.getHtmlText());
//    }

}