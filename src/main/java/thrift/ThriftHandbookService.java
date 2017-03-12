package thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import service.HandbookService;
import service.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThriftHandbookService implements HandbookService {

    HandbookThrift.Client client;

    @Setter String host;
    @Setter int port;

    public void setup() throws TTransportException {
        TSocket socket = new TSocket(host, port);
        socket.open();

        TBinaryProtocol protocol = new TBinaryProtocol(socket);
        client = new HandbookThrift.Client(protocol);
    }

    public Topic getTopic(long id) {
        try {
            return new Topic(id, client.getContent(id), "");
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Topic> findTopics(String keyword) {
        try {
            Map<Long, String> headers = client.findTopicsHeaders(keyword);
            ArrayList<Topic> topics = new ArrayList<>(headers.size());
            for (Map.Entry<Long, String> e : headers.entrySet()) {
                topics.add(new Topic(e.getKey(), "", e.getValue()));
            }
            return topics;
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public long createTopic(Topic topic) {
        try {
            return client.createTopic(topic.getHeader(), topic.getContent());
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateTopic(Topic topic) {
        try {
            client.updateTopic(topic.getId(), topic.getContent());
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
