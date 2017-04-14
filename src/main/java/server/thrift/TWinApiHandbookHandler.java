package server.thrift;

import common.exception.NoSuchEntityException;
import common.service.ConverterUtils;
import common.service.WinApiHandbookService;
import model.TWinApiClass;
import org.apache.thrift.TException;

import javax.transaction.Transactional;
import java.util.List;

import static common.service.ConverterUtils.convert;
import static java.util.stream.Collectors.toList;

@Transactional
public class TWinApiHandbookHandler implements TWinApiHandbookService.Iface {
    private final WinApiHandbookService service;

    public TWinApiHandbookHandler(WinApiHandbookService service){

        this.service = service;
    }

    @Override
    public TWinApiClass getClass(long id) throws TException {
        return convert(service.getWinApiClass(id));
    }

    @Override
    public List<TWinApiClass> findClass(String keyword) throws TException {
        return service.findClasses(keyword)
                .stream()
                .map(ConverterUtils::convert)
                .collect(toList());
    }

    @Override
    public List<TWinApiClass> findFunction(String keyword) throws TException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long createClass(TWinApiClass topic) throws TException {
        return service.createWinApiClass(convert(topic));
    }

    @Override
    public void updateClass(TWinApiClass topic) throws TNoSuchEntityException, TException {
        try {
            service.updateTopic(convert(topic));
        } catch (NoSuchEntityException e) {
            throw new TNoSuchEntityException(e.getMessage());
        }
    }

    @Override
    public void removeClass(long id) throws TException {
        service.removeTopic(id);
    }
}
