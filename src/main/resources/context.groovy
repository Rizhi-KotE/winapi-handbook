import gui.Browser
import gui.Editor
import gui.FindTopicsWidget
import javafx.beans.property.SimpleObjectProperty
import service.DummyHandbookService
import service.Topic
import thrift.ThriftHandbookService

beans {
    handbookService(ThriftHandbookService) { bean ->
        bean.initMethod = 'setup'
        host = 'localhost'
        port = 9090
    }
//    handbookService(DummyHandbookService) { bean ->
//        bean.initMethod = 'setup'
//        files = ["HTMLEditor.html": 1l]
//        topics = [2l: new Topic(2l, "asdfasdf", "2"),
//                  3l: new Topic(3l, "qwerqwerqwer", "3")]
//    }
    currentTopicProperty(SimpleObjectProperty) {
        value = new Topic()
    }

    findView(FindTopicsWidget) { bean ->
        bean.initMethod = 'setup'
        findMessage = "find"
        closeMessage = "close"
        service = handbookService
        currentTopic = currentTopicProperty
    }
    editorBean(Editor) { bean ->
        bean.initMethod = 'setup'
        service = handbookService
        currentTopic = currentTopicProperty
    }
    browserBean(Browser) { bean ->
        currentTopic = currentTopicProperty
        bean.initMethod = 'setup'
        bean.lazyInit = true
        service = handbookService
        find = findView
        editor = editorBean
    }
//    viewSampler(gui.WebViewSample) {
//        browser = browserBean
//    }
}