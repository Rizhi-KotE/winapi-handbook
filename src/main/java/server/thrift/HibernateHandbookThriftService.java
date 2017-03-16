package server.thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TException;
import common.service.ConverterUtils;
import common.service.Topic;

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static common.service.ConverterUtils.convert;


public class HibernateHandbookThriftService implements HandbookThrift.Iface {

    @Setter
    TopicRepository repository;

    @Override
    public model.Topic getTopic(long id) throws TException {
        return convert(ofNullable(repository.findOne(id)).orElse(new Topic()));
    }

    @Override
    public List<model.Topic> findTopicsHeaders(String keyword) throws TException {
        return repository
                .findByHeaderContaining(keyword)
                .stream()
                .map(ConverterUtils::convert)
                .collect(toList());
    }

    @Override
    public long createTopic(model.Topic topic) throws TException {
        return repository.save(convert(topic)).getId();
    }

    @Override
    public void updateTopic(model.Topic topic) throws TException {
        repository.save(convert(topic));
    }

    @Override
    public void removeTopic(long id) throws TException {
        repository.delete(id);
    }
}
