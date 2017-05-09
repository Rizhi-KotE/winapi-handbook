package common.service.impl;

import common.exception.HandbookException;
import model.WinApiClass;
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
    public WinApiClass getWinApiClass(long id) throws HandbookException {
        return service.getWinApiClass(id);
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        return service.findClasses(keyword);
    }

    @Override
    public WinApiClass saveOrUpdate(WinApiClass winApiClass) throws HandbookException {
        return service.saveOrUpdate(winApiClass);
    }

    @Override
    public int removeClass(long id) throws HandbookException {
        int result;
        if ((result = service.removeClass(id)) == 0) {
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
}
