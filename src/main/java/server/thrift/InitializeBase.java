package server.thrift;

import lombok.Setter;
import common.service.Topic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * Created by rizhi-kote on 14.03.17.
 */
public class InitializeBase {

    @Setter
    TopicRepository repository;

    @Setter
    Map<Long, String> content;

    public void setup() {
        for (Map.Entry<Long, String> name : content.entrySet()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name.getValue());
            if (stream == null) throw new RuntimeException("resource not found " + name.getKey());
            String html = new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
            repository.save(new Topic(name.getKey(), html, name.getValue()));
        }
    }
}
