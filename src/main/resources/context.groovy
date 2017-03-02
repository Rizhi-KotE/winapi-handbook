import gui.Browser
import gui.FindTopicsWidget
import service.DummyHandbookService
import service.Topic

beans {
//    thriftHandbookService(HandbookThrift.Client) {
//
//        def socket = new TSocket("localhost", 9090)
//        socket.open()
//
//        def protocol = new TBinaryProtocol(socket)
//        new HandbookThrift.Client(protocol)
//    }
    dummyHandbookService(DummyHandbookService) { bean ->
        bean.initMethod = 'setup'
        files = ["HTMLEditor.html": 1l]
        topics = [2l: new Topic(2l, "asdfasdf", "2"),
                  3l: new Topic(3l, "qwerqwerqwer", "3")]
    }
//    handbookWindow(HandbookWindow) { bean ->
//        bean.initMethod = 'setup'
//        service = thriftHandbookService
//        width = 640
//        height = 480
//        title = 'WinAPI handbook'
//    }
    findView(FindTopicsWidget){ bean ->
        bean.initMethod = 'setup'
        findMessage = "find"
        closeMessage = "close"
        service = dummyHandbookService
    }
    browserBean(Browser) { bean ->
        bean.initMethod = 'setup'
        bean.lazyInit = true
        service = dummyHandbookService
        find = findView
    }
//    viewSampler(gui.WebViewSample) {
//        browser = browserBean
//    }
}