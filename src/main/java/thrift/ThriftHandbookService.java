package thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import service.ConverterUtils;
import service.HandbookService;
import service.Topic;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static service.ConverterUtils.convert;

public class ThriftHandbookService implements HandbookService {

    HandbookThrift.Client client;

    @Setter
    String host;
    @Setter
    int port;

    public void setup() throws TTransportException {
        TSocket socket = new TSocket(host, port);
        socket.open();

        TBinaryProtocol protocol = new TBinaryProtocol(socket);
        client = new HandbookThrift.Client(protocol);
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
