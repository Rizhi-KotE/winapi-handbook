package common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.exception.HandbookException;
import model.WinApiUserElement;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InitBase {
    public InitBase(WinApiHandbookService service, List<String> files) throws IOException, HandbookException {
        ObjectMapper mapper = new ObjectMapper();
        for (String name : files) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name);
            if (stream == null) throw new RuntimeException("resource not found " + name);
            WinApiUserElement element = mapper.readValue(stream, WinApiUserElement.class);
            service.saveOrUpdateUserElement(element);
        }
    }

    public static void main(String[] args) {
        new GenericGroovyApplicationContext(new ClassPathResource("serverContext.groovy"));

    }
}
