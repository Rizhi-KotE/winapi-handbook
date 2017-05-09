package client.service;

import common.exception.HandbookException;
import common.service.impl.WinApiHandbookService;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;

import java.util.List;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class HandbookSoapAdapter implements WinApiHandbookService {

    @Override
    public WinApiClass getWinApiClass(long id) throws HandbookException {
        return null;
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        return null;
    }

    @Override
    public WinApiClass saveOrUpdate(WinApiClass winApiClass) throws HandbookException {
        return null;
    }

    @Override
    public int removeClass(long id) throws HandbookException {
        return 0;
    }

    @Override
    public WinApiFunction createFunction(long classId, WinApiFunction function) throws HandbookException {
        return null;
    }

    @Override
    public WinApiParameter createParam(long functionId, WinApiParameter parameter) throws HandbookException {
        return null;
    }

    @Override
    public int updateFunction(WinApiFunction function) throws HandbookException {
        return 0;
    }

    @Override
    public int removeWinApiFunction(long id) throws HandbookException {
        return 0;
    }

    @Override
    public int updateParam(WinApiParameter parameter) throws HandbookException {
        return 0;
    }

    @Override
    public int removeWinApiParameter(long id) throws HandbookException {
        return 0;
    }
}
