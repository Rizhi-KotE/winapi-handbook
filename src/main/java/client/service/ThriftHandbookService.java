package client.service;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.*;
import common.service.ConverterUtils;
import common.service.HandbookService;
import common.service.Topic;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static common.service.ConverterUtils.convert;

public class ThriftHandbookService implements HandbookService {

    HandbookThrift.Client client;

    @Setter
    String host;

    @Setter
    int port;

    public void setup() throws TTransportException, IOException {
        TTransport transport = new TFramedTransport(new TSocket(host, port));

        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        client = new HandbookThrift.Client(protocol);
        transport.open();
        System.out.println("Run thrift client");
    }

    public Topic getTopic(long id) {
        try {
            return convert(client.getTopic(id));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Topic> findTopics(String keyword) {
        try {
            return client.findTopicsHeaders(keyword)
                    .stream()
                    .map(ConverterUtils::convert)
                    .collect(toList());
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public long createTopic(Topic topic) {
        try {
            return client.createTopic(convert(topic));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateTopic(Topic topic) {
        try {
            client.updateTopic(convert(topic));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeTopic(long id) {
        try {
            client.removeTopic(id);
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
