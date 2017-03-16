import client.gui.Browser
import client.gui.Editor
import client.gui.FindTopicsWidget
import client.service.HandbookSoapAdapter
import client.service.SoupHandbookServiceFactory
import javafx.beans.property.SimpleObjectProperty
import common.service.Topic

beans {
//    handbookService(ThriftHandbookService) { bean ->
//        bean.initMethod = 'setup'
//        host = 'localhost'
//        port = 9090
//    }

    soapHandbookServiceFactory(SoupHandbookServiceFactory){
        url = "http://localhost:1986/wss/hello?wsdl"
        namespaceUri = "http://soap.server/"
        localPart = "HandbookSoapServiceImplService";
    }

    handbookSoupService(soapHandbookServiceFactory: 'createService')

    handbookService(HandbookSoapAdapter, handbookSoupService)

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
//    viewSampler(client.gui.WebViewSample) {
//        browser = browserBean
//    }
}