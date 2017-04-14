package server.thrift;

import common.exception.HandbookException;
import common.service.ConverterUtils;
import common.service.WinApiHandbookService;
import model.TWinApiClass;
import org.apache.thrift.TException;

import java.util.List;

import static common.service.ConverterUtils.convert;
import static java.util.stream.Collectors.toList;

public class TWinApiHandbookHandler implements TWinApiHandbookService.Iface {
    private final WinApiHandbookService service;

    public TWinApiHandbookHandler(WinApiHandbookService service) {

        this.service = service;
    }

    @Override
    public TWinApiClass getClass(long id) throws TException {
        try {
            return convert(service.getWinApiClass(id));
        } catch (HandbookException e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TWinApiClass> findClass(String keyword) throws TException {
        try {
            return service.findClasses(keyword)
                    .stream()
                    .map(ConverterUtils::convert)
                    .collect(toList());
        } catch (HandbookException e) {
            throw new TException(e);
        }
    }

    @Override
    public List<TWinApiClass> findFunction(String keyword) throws TException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long createClass(TWinApiClass topic) throws TException {
        try {
            return service.createWinApiClass(convert(topic));
        } catch (HandbookException e) {
            throw new TException(e);
        }
    }

    @Override
    public void updateClass(TWinApiClass topic) throws TException {
        try {
            service.updateClass(convert(topic));
        } catch (HandbookException e) {
            throw new TException(e);
        }
    }

    @Override
    public void removeClass(long id) throws TException {
        try {
            service.removeTopic(id);
        } catch (HandbookException e) {
            throw new TException(e);
        }
    }
}
