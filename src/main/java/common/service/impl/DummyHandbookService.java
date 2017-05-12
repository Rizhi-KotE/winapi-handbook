package common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.exception.HandbookException;
import lombok.Setter;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import model.WinApiUserElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class DummyHandbookService implements WinApiHandbookService {

    @Setter
    List<String> files;

    @Setter
    HashMap<Long, WinApiUserElement> topics = new HashMap<>();

    public void setup() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        for (String name : files) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name);
            if (stream == null) throw new RuntimeException("resource not found " + name);
            WinApiUserElement element = mapper.readValue(stream, WinApiUserElement.class);
            topics.put(element.getId(), element);
        }
    }

    @Override
    public List<WinApiUserElement> getAll() throws HandbookException {
        return new ArrayList<>(topics.values());
    }

    @Override
    public WinApiUserElement getUserElement(long id) throws HandbookException {
        return topics.get(id);
    }

    @Override
    public WinApiUserElement saveOrUpdateUserElement(WinApiUserElement winApiUserElement) throws HandbookException {
        return topics.put(winApiUserElement.getId(), winApiUserElement);
    }

    @Override
    public int removeElement(long id) throws HandbookException {
        topics.remove(id);
        return 1;
    }

    @Override
    public WinApiFunction createFunction(long classId, WinApiFunction function) throws HandbookException {
        WinApiUserElement element = topics.get(classId);
        element.getFunctions().add(function);
        return function;
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
    public WinApiParameter createParam(long functionId, WinApiParameter parameter) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                if (f.getId() == functionId) {
                    f.getParams().add(parameter);
                }
            });
        });
        return parameter;
    }

    @Override
    public int updateParam(WinApiParameter parameter) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                for (int i = 0; i < e.getFunctions().size(); i++) {
                    if (f.getParams().get(i).getId() == parameter.getId()) {
                        f.getParams().set(i, parameter);
                    }
                }
            });
        });
        return 1;
    }

    @Override
    public int removeWinApiParameter(long id) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                f.getParams().removeIf(p -> p.getId() == id);
            });
        });
        return 1;
    }

    @Override
    public WinApiFunctionRequirement createRequirement(long functionId, WinApiFunctionRequirement requirement) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                if (f.getId() == functionId) {
                    f.getRequirements().add(requirement);
                }
            });
        });
        return requirement;
    }

    @Override
    public int updateRequirement(WinApiFunctionRequirement requirement) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                for (int i = 0; i < e.getFunctions().size(); i++) {
                    if (f.getRequirements().get(i).getId() == requirement.getId()) {
                        f.getRequirements().set(i, requirement);
                    }
                }
            });
        });
        return 1;    }

    @Override
    public int removeRequirement(long id) throws HandbookException {
        Collection<WinApiUserElement> element = topics.values();
        element.forEach(e -> {
            e.getFunctions().forEach(f -> {
                f.getRequirements().removeIf(p -> p.getId() == id);
            });
        });
        return 1;
    }
}
