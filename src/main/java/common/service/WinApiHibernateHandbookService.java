package common.service;

import common.exception.NoSuchEntityException;
import lombok.Setter;
import model.WinApiClass;

import java.util.List;

import static java.util.Optional.ofNullable;

public class WinApiHibernateHandbookService implements WinApiHandbookService {

    @Setter
    WinApiFunctionRepository functionRepository;

    @Setter
    WinApiClassRepository classRepository;

    @Override
    public WinApiClass getTopic(long id) {
        return classRepository.findOne(id);
    }

    @Override
    public List<WinApiClass> findTopics(String keyword) {
        return classRepository.findByNameContaining(keyword);
    }

    @Override
    public long createTopic(WinApiClass topic) {
        return classRepository.save(topic).getId();
    }

    @Override
    public void updateTopic(WinApiClass topic) throws NoSuchEntityException {
        ofNullable(classRepository.findOne(topic.getId()))
                .orElseThrow(() -> new NoSuchEntityException(topic));
    }

    @Override
    public void removeTopic(long id) {
        classRepository.delete(id);
    }
}
