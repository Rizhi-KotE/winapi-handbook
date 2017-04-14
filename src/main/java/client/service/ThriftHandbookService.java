package client.service;

import common.exception.HandbookException;
import common.service.ConverterUtils;
import common.service.WinApiHandbookService;
import lombok.Setter;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import server.thrift.TWinApiHandbookService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static common.service.ConverterUtils.convert;

public class ThriftHandbookService implements WinApiHandbookService {

    @Setter
    TWinApiHandbookService.Iface client;


    @Setter
    String host;

    @Setter
    int port;

    public void setup() throws TTransportException, IOException {
        TTransport transport = new TFramedTransport(new TSocket(host, port));

        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        client = new TWinApiHandbookService.Client(protocol);
        transport.open();
        System.out.println("Run thrift client");
    }

    @Override
    public WinApiClass getWinApiClass(long id) throws HandbookException {
        try {
            return convert(client.getWinApiClass(id));
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        try {
            return client.findClasses(keyword)
                    .stream()
                    .map(ConverterUtils::convert)
                    .collect(Collectors.toList());
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public WinApiClass saveOrUpdate(WinApiClass topic) throws HandbookException {
        try {
            return convert(client.createWinApiClass(convert(topic)));
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    public void updateClass(WinApiClass topic) throws HandbookException {
        try {
            client.updateClass(convert(topic));
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public void removeClass(long id) throws HandbookException {
        try {
            client.removeClass(id);
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdateFunction(WinApiFunction function) throws HandbookException {
        try {
            client.updateFunction(convert(function));
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public void removeWinApiFunction(long id) throws HandbookException {
        try {
            client.removeFunction(id);
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdateParameter(WinApiParameter parameter) throws HandbookException {
        try {
            client.updateParameter(convert(parameter));
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }

    @Override
    public void removeWinApiParameter(long id) throws HandbookException {
        try {
            client.removeParameter(id);
        } catch (TException e) {
            throw new HandbookException(e.getMessage());
        }
    }
}
