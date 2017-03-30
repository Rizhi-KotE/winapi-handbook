package server.soap;

import common.service.HandbookSoapService;
import lombok.Setter;
import common.service.WinApiClassRepository;
import model.WinApiClass;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "common.service.HandbookSoapService")
public class HandbookSoapServiceImpl implements HandbookSoapService {
    @Setter
    WinApiClassRepository repository;

    @Override
    public WinApiClass getTopic(long id) {
        return repository.findOne(id);
    }

    @Override
    public WinApiClass[] findTopics(String keyword) {
        List<WinApiClass> topics = repository.findByNameContaining(keyword);
        return topics.toArray(new WinApiClass[topics.size()]);
    }

    @Override
    public long createTopic(WinApiClass topic) {
        return repository.save(topic).getId();
    }

    @Override
    public void updateTopic(WinApiClass topic) {
        repository.save(topic);
    }

    @Override
    public void removeTopic(long id) {
        repository.delete(id);
    }
}
