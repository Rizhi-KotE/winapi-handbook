package client.gui;

import common.service.WinApiHandbookService;
import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import model.WinApiClass;
import org.reactfx.EventSource;

import java.util.List;

@Setter @Getter
public class WinApiHandbookReactor {

    private final WinApiHandbookService service;

    public WinApiHandbookReactor(WinApiHandbookService service){

        this.service = service;
    }

    EventSource<WinApiClass> classEventSource = new EventSource<>();
    EventSource<List<WinApiClass>> listEventSource = new EventSource<>();
    EventSource<ActionEvent> editEventSource = new EventSource<>();
    EventSource<ActionEvent> findEventSource = new EventSource<>();
    EventSource<ActionEvent> updateEventSource = new EventSource<>();
    EventSource<ActionEvent> refreshEventSource = new EventSource<>();


    public void pushClass(WinApiClass winApiClass) {
        classEventSource.push(winApiClass);
    }

    public void search(String text) {
//        listEventSource.push(service.findClasses(text));
    }

    public void save(WinApiClass winApiClass) {
//        service.createWinApiClass(winApiClass);
        updateEventSource.push(new ActionEvent());
    }

    public void delete(WinApiClass winApiClass) {
//        service.removeTopic(winApiClass.getId());
        refreshEventSource.push(new ActionEvent());
    }
}
