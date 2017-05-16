package client.gui;

import common.exception.HandbookException;
import common.service.impl.RestClientService;
import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import model.WinApiUserElement;
import org.reactfx.EventSource;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class WinApiHandbookReactor {

    private final RestClientService service;
    EventSource<WinApiUserElement> classEventSource = new EventSource<>();
    EventSource<List<WinApiUserElement>> listEventSource = new EventSource<>();
    EventSource<ActionEvent> editEventSource = new EventSource<>();
    EventSource<ActionEvent> findEventSource = new EventSource<>();
    EventSource<ActionEvent> updateEventSource = new EventSource<>();
    EventSource<ActionEvent> refreshEventSource = new EventSource<>();
    EventSource<Exception> exceptionEventSource = new EventSource<>();

    public WinApiHandbookReactor(RestClientService service) {

        this.service = service;
    }

    public void search(String text) {
        try {
            List<WinApiUserElement> elements = service.getAll().stream()
                    .filter(el -> el.getName().contains(text))
                    .collect(Collectors.toList());
            listEventSource.push(elements);
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void save(WinApiUserElement winApiUserElement) {
        try {
            service.saveOrUpdateUserElement(winApiUserElement);
            updateEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void delete(WinApiUserElement winApiUserElement) {
        try {
            service.removeElement(winApiUserElement.getId());
            refreshEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void removeFunction(WinApiFunction function) {
        try {
            service.removeWinApiFunction(function.getElementId(), function.getId());
            refreshEventSource.push(new ActionEvent());
            editEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void removeParameter(WinApiParameter winApiParameter) {
        try {
            service.removeWinApiParameter(winApiParameter.getElementId(), winApiParameter.getFunctionId(), winApiParameter.getId());
            refreshEventSource.push(new ActionEvent());
            editEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void removeRequirement(WinApiFunctionRequirement requirement) {
        try {
            service.removeRequirement(requirement.getElementId(), requirement.getFunctionId(), requirement.getId());
            refreshEventSource.push(new ActionEvent());
            editEventSource.push(new ActionEvent());
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void chooseClass(long id) {
        try {
            WinApiUserElement userElement = service.getUserElement(id);
            pushClass(userElement);
        } catch (HandbookException e) {
            exceptionEventSource.push(e);
        }
    }

    public void pushClass(WinApiUserElement winApiUserElement) {
        classEventSource.push(winApiUserElement);
    }
}
