package thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;
import service.Topic;

import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;


public class HibernateHandbookThriftService implements HandbookThrift.Iface {

    @Setter
    TopicRepository repository;

    @Override
    public String getContent(long id) throws TException {
        return repository.findOne(id).getContent();
    }

    @Override
    public Map<Long, String> findTopicsHeaders(String keyword) throws TException {
        return repository
                .findByHeaderContaining(keyword)
                .stream()
                .collect(toMap(Topic::getId, Topic::getHeader));
    }

    @Override
    public long createTopic(String name, String content) throws TException {
        Topic topic = new Topic(0, content, name);
        Topic saved = repository.save(topic);
        return saved.getId();
    }

    @Override
    public void updateTopic(long id, String content) throws TException {
        Topic updated = ofNullable(repository.findOne(id)).orElse(new Topic(0,"",""));
        updated.setContent(content);
        repository.save(updated);
    }

    @Override
    public void removeTopic(long id) throws TException {
        repository.delete(id);
    }
}
