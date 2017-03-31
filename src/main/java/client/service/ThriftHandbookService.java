package client.service;

import common.exception.NoSuchEntityException;
import common.service.ConverterUtils;
import common.service.WinApiHandbookService;
import lombok.Setter;
import model.WinApiClass;
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
    public WinApiClass getWinApiClass(long id) {
        try {
            return convert(client.getClass(id));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) {
        try {
            return client.findClass(keyword).stream().map(ConverterUtils::convert).collect(toList());
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public long createWinApiClass(WinApiClass topic) {
        try {
            return client.createClass(convert(topic));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTopic(WinApiClass topic) throws NoSuchEntityException {
        try {
            client.updateClass(convert(topic));
        } catch (TNoSuchEntityException e) {
            throw new NoSuchEntityException(e.getMessage());
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeTopic(long id) {
        try {
            client.removeClass(id);
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
