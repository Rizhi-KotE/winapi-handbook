package server.thrift;

import common.exception.HandbookException;
import common.service.ConverterUtils;
import common.service.WinApiHandbookService;
import model.TWinApiClass;
import model.TWinApiFunction;
import model.TWinApiParams;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import java.util.List;

import static common.service.ConverterUtils.convert;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class TWinApiHandbookHandler implements TWinApiHandbookService.Iface {
    private static Logger log = Logger.getLogger(TWinApiHandbookService.class);
    private final WinApiHandbookService service;

    public TWinApiHandbookHandler(WinApiHandbookService service) {

        this.service = service;
    }


    @Override
    public TWinApiClass getWinApiClass(long id) throws THandbookException {
        try {
            log.debug(format("Get class [id=%d]", id));
            return convert(service.getWinApiClass(id));
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public List<TWinApiClass> findClasses(String keyword) throws THandbookException {
        try {
            log.debug(format("Find classes [keyword=%s]", keyword));
            return service.findClasses(keyword)
                    .stream()
                    .map(ConverterUtils::convert)
                    .collect(toList());
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public TWinApiClass createWinApiClass(TWinApiClass topic) throws TException {
        try {
            log.debug(format("Create class [name=%s]", topic.getName()));
            return convert(service.saveOrUpdate(convert(topic)));
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void updateClass(TWinApiClass topic) throws TException {
        try {
            log.debug(format("Update class [id=%d]", topic.getId()));
            service.saveOrUpdate(convert(topic));
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void removeClass(long id) throws TException {
        try {
            log.debug(format("Remove class [id=%d]", id));
            service.removeClass(id);
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void updateFunction(TWinApiFunction func) throws THandbookException {
        try {
            log.debug(format("Update function [id=%d]", func.getId()));
            service.saveOrUpdateFunction(convert(func));
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void removeFunction(long id) throws THandbookException {
        try {
            log.debug(format("Update function [id=%d]", id));
            service.removeWinApiFunction(id);
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void updateParameter(TWinApiParams params) throws THandbookException {
        try {
            log.debug(format("Update parameter [id=%d]", params.getId()));
            service.saveOrUpdateParameter(convert(params));
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }

    @Override
    public void removeParameter(long id) throws THandbookException {
        try {
            log.debug(format("Remove parameter [id=%d]", id));
            service.removeWinApiParameter(id);
        } catch (HandbookException e) {
            throw new THandbookException(e.getMessage());
        }
    }
}
