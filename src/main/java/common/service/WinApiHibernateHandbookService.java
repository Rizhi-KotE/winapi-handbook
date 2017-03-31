package common.service;

import common.exception.NoSuchEntityException;
import model.WinApiClass;

import java.util.List;

import static java.util.Optional.ofNullable;

public class WinApiHibernateHandbookService implements WinApiHandbookService {

    public WinApiHibernateHandbookService(WinApiFunctionRepository functionRepository, WinApiClassRepository classRepository){

        this.functionRepository = functionRepository;
        this.classRepository = classRepository;
    }

    final WinApiFunctionRepository functionRepository;

    final WinApiClassRepository classRepository;

    @Override
    public WinApiClass getWinApiClass(long id) {
        return classRepository.findOne(id);
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) {
        return classRepository.findByNameContaining(keyword);
    }

    @Override
    public long createWinApiClass(WinApiClass topic) {
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
