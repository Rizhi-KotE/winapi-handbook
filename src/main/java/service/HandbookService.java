package service;

import java.util.Collection;

/**
 * Created by rizhi-kote on 28.02.17.
 */
public interface HandbookService {
    Topic getTopic(long id);

    Collection<Topic> findTopics(String keyword);

    void updateTopic(Topic topic);

    void removeTopic(long id);
}
