import gui.Browser
import service.DummyHandbookService

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
        topics = [2l: "asdfasdf",
                  3l: "qwerqwerqwer"]
    }
//    handbookWindow(HandbookWindow) { bean ->
//        bean.initMethod = 'setup'
//        service = thriftHandbookService
//        width = 640
//        height = 480
//        title = 'WinAPI handbook'
//    }
    browserBean(Browser) { bean ->
        bean.initMethod = 'setup'
        bean.lazyInit = true
        service = dummyHandbookService
    }
//    viewSampler(gui.WebViewSample) {
//        browser = browserBean
//    }
}