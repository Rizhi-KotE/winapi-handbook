package gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

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

}