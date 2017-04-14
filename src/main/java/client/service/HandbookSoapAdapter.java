package client.service;

import common.exception.HandbookException;
import common.service.WinApiHandbookService;
import common.service.HandbookSoapService;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;

import java.util.Arrays;
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
    public WinApiClass saveOrUpdate(WinApiClass topic) throws HandbookException {
        return null;
    }

    @Override
    public void removeClass(long id) throws HandbookException {

    }

    @Override
    public void saveOrUpdateFunction(WinApiFunction function) throws HandbookException {

    }

    @Override
    public void removeWinApiFunction(long id) throws HandbookException {

    }

    @Override
    public void saveOrUpdateParameter(WinApiParameter parameter) throws HandbookException {

    }

    @Override
    public void removeWinApiParameter(long id) throws HandbookException {

    }
}
