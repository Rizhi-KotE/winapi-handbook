import client.gui.Browser
import client.gui.Editor
import client.gui.FindTopicsWidget
import client.service.HandbookSoapAdapter
import client.service.SoupHandbookServiceFactory
import client.service.ThriftHandbookService
import common.service.Topic
import javafx.beans.property.SimpleObjectProperty

beans {

    switch (System.getProperty("protocol")) {
        case "soap":
            handbookServiceFactory(SoupHandbookServiceFactory) {
                url = "http://localhost:1986/wss/hello?wsdl"
                namespaceUri = "http://soap.server/"
                localPart = "HandbookSoapServiceImplService";
            }

            handbookSoupService(handbookServiceFactory: 'createService')

            handbookService(HandbookSoapAdapter, handbookSoupService) { bean ->
                bean.initMethod = 'setup'
            }
            break;
        case "thrift":
            handbookService(ThriftHandbookService) { bean ->
                bean.initMethod = 'setup'
                host = 'localhost'
                port = 9090
            }
    }




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
}