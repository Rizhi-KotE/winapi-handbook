package service;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThriftHandbookService implements HandbookService {

    @Setter
    HandbookThrift.Client client;

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
            return client.createTopic(topic.header, topic.content);
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateTopic(Topic topic) {
        try {
            client.updateTopic(topic.id, topic.content);
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
