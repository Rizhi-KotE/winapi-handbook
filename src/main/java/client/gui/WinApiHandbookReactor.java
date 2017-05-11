package client.gui;

import common.exception.HandbookException;
import common.service.impl.WinApiHandbookService;
import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import model.WinApiUserElement;
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

    EventSource<WinApiUserElement> classEventSource = new EventSource<>();
    EventSource<List<WinApiUserElement>> listEventSource = new EventSource<>();
    EventSource<ActionEvent> editEventSource = new EventSource<>();
    EventSource<ActionEvent> findEventSource = new EventSource<>();
    EventSource<ActionEvent> updateEventSource = new EventSource<>();
    EventSource<ActionEvent> refreshEventSource = new EventSource<>();


    public void pushClass(WinApiUserElement winApiUserElement) {
        classEventSource.push(winApiUserElement);
    }

    public void search(String text) {
        try {
            throw new HandbookException();
//            listEventSource.push(service.findClasses(text));
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void save(WinApiUserElement winApiUserElement) {
        try {
            service.saveOrUpdateUserElement(winApiUserElement);
            updateEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void delete(WinApiUserElement winApiUserElement) {
        try {
            service.removeElement(winApiUserElement.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void removeFunction(WinApiFunction function) {
        try {
            service.removeElement(function.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }

    public void removeParameter(WinApiParameter winApiParameter) {
        try {
            service.removeElement(winApiParameter.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            e.printStackTrace();
        }
    }
}
