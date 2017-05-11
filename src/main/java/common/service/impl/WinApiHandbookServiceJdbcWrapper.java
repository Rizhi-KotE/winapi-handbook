package common.service.impl;

import common.exception.HandbookException;
import model.WinApiFunctionRequirement;
import model.WinApiUserElement;
import model.WinApiFunction;
import model.WinApiParameter;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.String.format;

public class WinApiHandbookServiceJdbcWrapper implements WinApiHandbookService {

    private final WinApiHandbookServiceJdbc service;

    public WinApiHandbookServiceJdbcWrapper(DataSource dataSource) {
        service = new WinApiHandbookServiceJdbc(dataSource);
    }

    @Override
    public WinApiUserElement getUserElement(long id) throws HandbookException {
        return service.getUserElement(id);
    }

    @Override
    public WinApiUserElement saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException {
        return service.saveOrUpdateUserElement(winApiUserElement);
    }

    @Override
    public int removeElement(long id) throws HandbookException {
        int result;
        if ((result = service.removeElement(id)) == 0) {
            throw new HandbookException(format("Class [id=%d] was not deleted",id));
        }
        return result;
    }

    @Override
    public WinApiFunction createFunction(long classId, WinApiFunction function) throws HandbookException {
        return service.createFunction(classId, function);
    }

    @Override
    public WinApiParameter createParam(long functionId, WinApiParameter parameter) throws HandbookException {
        return service.createParam(functionId, parameter);
    }

    @Override
    public int updateFunction(WinApiFunction function) throws HandbookException {
        int result;
        if ((result = service.updateFunction(function)) == 0) {
            throw new HandbookException(format("Function [id=%d] was not updated",function.getId()));
        }
        return result;
    }

    @Override
    public int removeWinApiFunction(long id) throws HandbookException {
        int result;
        if ((result = service.removeWinApiFunction(id)) == 0) {
            throw new HandbookException(format("Function [id=%d] was not deleted",id));
        }
        return result;
    }

    @Override
    public int updateParam(WinApiParameter parameter) throws HandbookException {
        int result;
        if ((result = service.updateParam(parameter)) == 0) {
            throw new HandbookException(format("Parameter [id=%d] was not updated",parameter.getId()));
        }
        return result;
    }

    @Override
    public int removeWinApiParameter(long id) throws HandbookException {
        int result;
        if ((result = service.removeWinApiParameter(id)) == 0) {
            throw new HandbookException(format("Parameter [id=%d] was not deleted",id));
        }
        return result;
    }

    @Override
    public WinApiFunctionRequirement createRequirement(long functionId, WinApiFunctionRequirement requirement) throws HandbookException {
        return null;
    }

    @Override
    public int updateRequirement(WinApiFunctionRequirement requirement) throws HandbookException {
        return 0;
    }

    @Override
    public int removeRequirement(long id) throws HandbookException {
        return 0;
    }
}
