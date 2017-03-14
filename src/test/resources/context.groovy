import gui.Browser
import gui.Editor
import gui.FindTopicsWidget
import javafx.beans.property.SimpleObjectProperty
import service.DummyHandbookService
import service.Topic

beans {
    dummyHandbookService(DummyHandbookService) { bean ->
        bean.initMethod = 'setup'
        files = new HashMap<>()
        topics = new HashMap<>()
    }
    currentTopicProperty(SimpleObjectProperty) {
        value = new Topic()
    }

    findView(FindTopicsWidget) { bean ->
        bean.initMethod = 'setup'
        findMessage = "find"
        closeMessage = "close"
        service = dummyHandbookService
        currentTopic = currentTopicProperty
    }
    editorBean(Editor) { bean ->
        bean.initMethod = 'setup'
        service = dummyHandbookService
        currentTopic = currentTopicProperty
    }
    browserBean(Browser) { bean ->
        currentTopic = currentTopicProperty
        bean.initMethod = 'setup'
        bean.lazyInit = true
        service = dummyHandbookService
        find = findView
        editor = editorBean
    }
}