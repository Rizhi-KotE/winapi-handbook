package service;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class DummyHandbookService implements HandbookService {

    @Setter
    HashMap<String, Long> files;

    @Setter
    HashMap<Long, Topic> topics = new HashMap<>();

    public void setup(){
        for(Map.Entry<String, Long> name: files.entrySet()){
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name.getKey());
            if(stream == null) throw new RuntimeException("resource not found " + name.getKey());
            String html = new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
            topics.put(name.getValue(), new Topic(name.getValue(), html));
        }
    }

    @Override
    public Topic getTopic(long id) {
        return topics.get(id);
    }

    @Override
    public Collection<Topic> findTopics(String keyword) {
        return topics.values();
    }

    @Override
    public void updateTopic(Topic topic) {
        topics.put(topic.getId(), topic);
    }

    public HashMap<Long, Topic> getTopics() {
        return topics;
    }

    public void setTopics(HashMap<Long, Topic> topics) {
        this.topics = topics;
    }

    @Override
    public void removeTopic(long id) {
        topics.remove(id);
    }
}
