package server.soap;

import common.service.HandbookSoapService;
import common.service.Topic;
import lombok.Setter;
import server.thrift.TopicRepository;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "common.service.HandbookSoapService")
public class HandbookSoapServiceImpl implements HandbookSoapService {
    @Setter
    TopicRepository repository;

    @Override
    public Topic getTopic(long id) {
        return repository.findOne(id);
    }

    @Override
    public Topic[] findTopics(String keyword) {
        List<Topic> topics = repository.findByHeaderContaining(keyword);
        return topics.toArray(new Topic[topics.size()]);
    }

    @Override
    public long createTopic(Topic topic) {
        return repository.save(topic).getId();
    }

    @Override
    public void updateTopic(Topic topic) {
        repository.save(topic);
    }

    @Override
    public void removeTopic(long id) {
        repository.delete(id);
    }
}
