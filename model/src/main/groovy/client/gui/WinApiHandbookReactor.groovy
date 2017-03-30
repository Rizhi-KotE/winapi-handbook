package client.gui

import model.WinApiClass
import org.reactfx.EventSource

class WinApiHandbookReactor {
    EventSource<WinApiClass> classEventSource = new EventSource<>()

    void pushClass(WinApiClass winApiClass) {
        classEventSource.push(winApiClass);
    }
}
