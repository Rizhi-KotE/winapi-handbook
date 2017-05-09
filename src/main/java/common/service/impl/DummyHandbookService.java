package common.service.impl;

import common.exception.HandbookException;
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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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
    public WinApiClass getWinApiClass(long id) throws HandbookException {
        return null;
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) throws HandbookException {
        return topics.entrySet().stream().filter(tuple -> tuple.getValue().getName().contains(keyword))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public WinApiClass saveOrUpdate(WinApiClass winApiClass) throws HandbookException {
        return topics.put(winApiClass.getId(), winApiClass);
    }

    @Override
    public int removeClass(long id) throws HandbookException {
        return 1;
    }

    @Override
    public WinApiFunction createFunction(long classId, WinApiFunction function) throws HandbookException {
        return function;
    }

    @Override
    public WinApiParameter createParam(long functionId, WinApiParameter parameter) throws HandbookException {
        return parameter;
    }

    @Override
    public int updateFunction(WinApiFunction function) throws HandbookException {

        return 0;
    }

    @Override
    public int removeWinApiFunction(long id) throws HandbookException {

        return 0;
    }

    @Override
    public int updateParam(WinApiParameter parameter) throws HandbookException {

        return 0;
    }

    @Override
    public int removeWinApiParameter(long id) throws HandbookException {

        return 0;
    }
}
