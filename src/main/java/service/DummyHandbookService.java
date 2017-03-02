package service;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DummyHandbookService implements HandbookService {

    @Setter
    HashMap<String, Long> files;

    @Setter
    HashMap<Long, Topic> topics = new HashMap<>();

    public void setup() {
        for (Map.Entry<String, Long> name : files.entrySet()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name.getKey());
            if (stream == null) throw new RuntimeException("resource not found " + name.getKey());
            String html = new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
            topics.put(name.getValue(), new Topic(name.getValue(), html, name.getValue().toString()));
        }
    }

    @Override
    public Topic getTopic(long id) {
        return topics.get(id);
    }

    @Override
    public List<Topic> findTopics(String keyword) {
        return topics.values().stream().filter(t -> t.getHeader().contains(keyword)).collect(toList());
    }

    @Override
    public long createTopic(Topic topic) {
        long i = topics.size() + 1;
        topic.setId(i);
        topics.put(i, topic);
        return i;
    }

    @Override
    public void updateTopic(Topic topic) {
        topics.put(topic.getId(), topic);
    }

    @Override
    public void removeTopic(long id) {
        topics.remove(id);
    }
}
