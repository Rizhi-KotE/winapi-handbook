package common.service.impl;

import common.exception.HandbookException;
import model.WinApiUserElement;

import java.util.List;

public interface RestClientService {
    List<WinApiUserElement> getAll() throws HandbookException;

    void saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException;

    void removeElement(long id) throws HandbookException;

    void removeWinApiFunction(long elementId, long id) throws HandbookException;

    void removeWinApiParameter(long elementId, long functionId, long id) throws HandbookException;

    void removeRequirement(long elementId, long functionId, long id) throws HandbookException;

    WinApiUserElement getUserElement(long id) throws HandbookException;
}
