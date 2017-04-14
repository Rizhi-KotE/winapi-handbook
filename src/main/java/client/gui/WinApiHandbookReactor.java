package client.gui;

import common.exception.HandbookException;
import common.service.WinApiHandbookService;
import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
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
        try {
            listEventSource.push(service.findClasses(text));
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void save(WinApiClass winApiClass) {
        try {
            service.saveOrUpdate(winApiClass);
            updateEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void delete(WinApiClass winApiClass) {
        try {
            service.removeClass(winApiClass.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void removeFunction(WinApiFunction function) {
        try {
            service.removeClass(function.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void removeParameter(WinApiParameter winApiParameter) {
        try {
            service.removeClass(winApiParameter.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }
}
