package common.service;

import common.exception.NoSuchEntityException;
import model.WinApiClass;

import java.util.List;

public interface WinApiHandbookService {
    WinApiClass getTopic(long id);

    List<WinApiClass> findTopics(String keyword);

    long createTopic(WinApiClass topic);

    void updateTopic(WinApiClass topic) throws NoSuchEntityException;

    void removeTopic(long id);
}
