package client.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rizhi-kote on 14.03.17.
 */
public class BrowserTest {

//    @Rule
//    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
//
//    private Browser browser;
//
//    @Before
//    public void setUp() throws Exception {
//        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext(new ClassPathResource("context.groovy"));
//        browser = context.getBean(Browser.class);
//    }
//
//    @Test
//    public void changeToBrowseStateShouldBeIdempotentic() throws Exception {
//        browser.changeToBrowseState(null);
//        browser.changeToBrowseState(null);
//    }
//
//    @Test
//    public void changeToEditorStateShouldBeIdempotentic() throws Exception {
//        browser.changeToEditorState(null);
//        browser.changeToEditorState(null);
//    }
//
//    @Test
//    public void createWinApiClass() throws Exception {
//        browser.editor.createNewTopic("newTopic");
//        browser.editor.htmlEditor.setHtmlText("content");
//        browser.saveTopic(null);
//        List<WinApiClass> topics = browser.service.findClasses("newTopic");
//        assertEquals("content", topics.get(0).getContent());
//    }
//
//    @Test
//    public void updateTopic() throws Exception {
//        WinApiClass topic = new WinApiClass(1l, "content", "header");
//        browser.service.createWinApiClass(topic);
//        browser.currentTopic.setValue(topic);
//        List<WinApiClass> topics = browser.service.findClasses("");
//        assertEquals("content", topics.get(0).getContent());
//        browser.editor.htmlEditor.setHtmlText("changed");
//        browser.saveTopic(null);
//        topics = browser.service.findClasses("");
//        assertEquals("changed", topics.get(0).getContent());
//    }
}