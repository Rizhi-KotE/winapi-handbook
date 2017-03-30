package client.gui

import io.reactivex.Observable
import model.WinApiClass
import org.reactfx.EventSource

class WinApiHandbookReactor {
    final EventSource<WinApiClass> classEventSource = Observable.empty()

    void pushClass(WinApiClass winApiClass) {
        classEventSource.push(winApiClass);
    }
}
