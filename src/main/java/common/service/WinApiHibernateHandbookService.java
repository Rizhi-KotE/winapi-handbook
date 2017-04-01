package common.service;

import common.exception.NoSuchEntityException;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Optional.ofNullable;

@Transactional
public class WinApiHibernateHandbookService implements WinApiHandbookService {

    public WinApiHibernateHandbookService(WinApiFunctionRepository functionRepository, WinApiClassRepository classRepository, WinApiParameterRepository paramsRepository){

        this.functionRepository = functionRepository;
        this.classRepository = classRepository;
        this.paramsRepository = paramsRepository;
    }

    final WinApiFunctionRepository functionRepository;

    final WinApiClassRepository classRepository;

    final WinApiParameterRepository paramsRepository;

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
//        for (WinApiFunction f : topic.getFunctions()) {
//            for (WinApiParameter p : f.getParams()) {
//                paramsRepository.save(p);
//            }
//            functionRepository.save(f);
//        }
      return   classRepository.saveAndFlush(topic).getId();
//        return classRepository.save(topic).getId();
    }

    @Override
    public void updateTopic(WinApiClass topic) throws NoSuchEntityException {
//        for (WinApiFunction f : topic.getFunctions()) {
//            for (WinApiParameter p : f.getParams()) {
//                paramsRepository.save(p);
//            }
//            functionRepository.save(f);
//        }
        classRepository.save(topic).getId();
    }

    @Override
    public void removeTopic(long id) {
        classRepository.delete(id);
    }
}
