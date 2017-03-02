package service;

import lombok.Getter;
import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Setter @Getter
public class ThriftHandbookService implements HandbookService {

    HandbookThrift.Client client;

    public Topic getTopic(long id) {
        try {
            return convertToHandbookTopic(client.getTopic(id));
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Collection<Topic> findTopics(String keyword) {
        try {
            return  client.findTopics(keyword).stream().map(this::convertToHandbookTopic).collect(toList());
        } catch (TException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateTopic(Topic topic) {
        try {
            client.updateTopic(convertToThriftTopic(topic));
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

    model.Topic convertToThriftTopic(Topic topic){
        return new model.Topic(topic.id, topic.content);
    }

    Topic convertToHandbookTopic(model.Topic topic){
       return new Topic(topic.getId(), topic.getContent());
    }
}
