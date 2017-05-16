package common.service.impl;

import common.exception.HandbookException;
import model.WinApiFunction;
import model.WinApiUserElement;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.String.format;

public class RestServiceJdbsWrapper implements RestClientService {

    private final WinApiHandbookServiceJdbc service;

    public RestServiceJdbsWrapper(DataSource ds) {
        service = new WinApiHandbookServiceJdbc(ds);
    }

    WinApiFunction restoreIds(WinApiFunction function) {
        function.getParams().forEach(p -> {
            p.setFunctionId(function.getId());
            p.setElementId(function.getElementId());
        });
        function.getRequirements().forEach(r -> {
            r.setFunctionId(function.getId());
            r.setElementId(function.getElementId());
        });
        return function;
    }

    @Override
    public List<WinApiUserElement> getAll() throws HandbookException {
        return service.getAll();
    }

    @Override
    public void saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException {
        service.saveOrUpdateUserElement(restoreIds(winApiUserElement));
    }

    WinApiUserElement restoreIds(WinApiUserElement element) {
        element.getFunctions().forEach(f -> f.setElementId(element.getId()));
        element.getFunctions().forEach(this::restoreIds);
        return element;
    }

    @Override
    public void removeElement(long id) throws HandbookException {
        service.removeElement(id);
    }

    @Override
    public void removeWinApiFunction(long elementId, long id) throws HandbookException {
        //if empty throws exception
        service.getUserElement(elementId);
        service.removeWinApiFunction(id);
    }

    @Override
    public void removeWinApiParameter(long elementId, long functionId, long id) throws HandbookException {
        //if empty throws exception
        WinApiUserElement userElement = service.getUserElement(elementId);
        if (userElement.getFunctions().stream().anyMatch(f -> f.getId() == functionId)) {
            service.removeWinApiParameter(id);
        } else {
            throw new HandbookException(format("cannot find function with [id=%d]", functionId));
        }

    }

    @Override
    public void removeRequirement(long elementId, long functionId, long id) throws HandbookException {
        //if empty throws exception
        WinApiUserElement userElement = service.getUserElement(elementId);
        if (userElement.getFunctions().stream().anyMatch(f -> f.getId() == functionId)) {
            service.removeRequirement(id);
        } else {
            throw new HandbookException(format("cannot find function with [id=%d]", functionId));
        }
    }

    @Override
    public WinApiUserElement getUserElement(long id) throws HandbookException {
        return restoreIds(service.getUserElement(id)) ;
    }
}
