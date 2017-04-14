package common.service.impl;

import common.exception.HandbookException;
import common.service.WinApiHandbookService;
import lombok.Setter;
import model.WinApiClass;
import model.WinApiFunction;
import model.WinApiParameter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DummyHandbookService implements WinApiHandbookService {

    @Setter
    HashMap<String, Long> files;

    @Setter
    HashMap<Long, WinApiClass> topics = new HashMap<>();

    public void setup() {
        for (Map.Entry<String, Long> name : files.entrySet()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name.getKey());
            if (stream == null) throw new RuntimeException("resource not found " + name.getKey());
            String html = new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
//            topics.put(name.getValue(), new WinApiClass(name.getValue(), html, name.getValue().toString()));
        }
    }

    @Override
    public WinApiClass getWinApiClass(long id) {
        return topics.get(id);
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) {
        return topics.values().stream().filter(t -> t.getName().contains(keyword)).collect(toList());
    }

    @Override
    public long createWinApiClass(WinApiClass topic) {
        long i = topics.size() + 1;
        topic.setId(i);
        topics.put(i, topic);
        return i;
    }

    @Override
    public void updateClass(WinApiClass topic) {
        if (!topics.containsKey(topic.getId())) throw new IllegalArgumentException(topic.toString());
        topics.put(topic.getId(), topic);
    }

    @Override
    public void removeTopic(long id) {
        topics.remove(id);
    }

    @Override
    public void updateWinApiFunction(WinApiFunction function) throws HandbookException {

    }

    @Override
    public void removeWinApiFunction(long id) throws HandbookException {

    }

    @Override
    public void updateWinApiParameter(WinApiParameter parameter) throws HandbookException {

    }

    @Override
    public void removeWinApiParameter(long id) throws HandbookException {

    }
}
