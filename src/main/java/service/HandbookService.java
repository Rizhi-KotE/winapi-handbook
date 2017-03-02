package service;

import java.util.List;

/**
 * Created by rizhi-kote on 28.02.17.
 */
public interface HandbookService {
    Topic getTopic(long id);

    List<Topic> findTopics(String keyword);

    long createTopic(Topic topic);

    void updateTopic(Topic topic);

    void removeTopic(long id);
}
