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
import server.thrift.TNoSuchEntityException;
import server.thrift.TWinApiHandbookService;

import java.io.IOException;
import java.util.List;

import static common.service.ConverterUtils.convert;
import static java.util.stream.Collectors.toList;

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
        return null;
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        return null;
    }

    @Override
    public long createWinApiClass(WinApiClass topic) throws HandbookException {
        return 0;
    }

    @Override
    public void updateClass(WinApiClass topic) throws HandbookException {

    }

    @Override
    public void removeTopic(long id) throws HandbookException {

    }

    @Override
    public void updateWinApiFunction(WinApiFunction function) throws HandbookException {

    }

    @Override
    public void removeWinApiFunction(long id) throws HandbookException {

    }

    @Override
    public void updateWinApiParameter(WinApiParameter parameter) throws HandbookException {

    }

    @Override
    public void removeWinApiParameter(long id) throws HandbookException {

    }
}
