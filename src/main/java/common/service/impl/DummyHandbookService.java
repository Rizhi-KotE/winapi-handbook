package common.service.impl;

import common.exception.HandbookException;
import lombok.Setter;
import model.WinApiFunctionRequirement;
import model.WinApiUserElement;
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
    HashMap<Long, WinApiUserElement> topics = new HashMap<>();

    public void setup() {
        for (Map.Entry<String, Long> name : files.entrySet()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name.getKey());
            if (stream == null) throw new RuntimeException("resource not found " + name.getKey());
            String html = new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
//            topics.put(name.getValue(), new WinApiUserElement(name.getValue(), html, name.getValue().toString()));
        }
    }

    @Override
    public WinApiUserElement getUserElement(long id) throws HandbookException {
        return null;
    }

    @Override
    public WinApiUserElement saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException {
        return topics.put(winApiUserElement.getId(), winApiUserElement);
    }

    @Override
    public int removeElement(long id) throws HandbookException {
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

    @Override
    public WinApiFunctionRequirement createRequirement(long functionId, WinApiFunctionRequirement requirement) throws HandbookException {
        return null;
    }

    @Override
    public int updateRequirement(WinApiFunctionRequirement requirement) throws HandbookException {
        return 0;
    }

    @Override
    public int removeRequirement(long id) throws HandbookException {
        return 0;
    }
}
