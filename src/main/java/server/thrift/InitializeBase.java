package server.thrift;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.exception.HandbookException;
import common.service.WinApiHandbookService;
import lombok.Setter;
import model.WinApiClass;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * Created by rizhi-kote on 14.03.17.
 */
public class InitializeBase {

    @Setter
    WinApiHandbookService service;

    @Setter
    List<String> content;

    public void setup() throws IOException, HandbookException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (String fileName : content) {
            WinApiClass[] winApiClasses = objectMapper.readValue(new ClassPathResource(fileName).getURL(), WinApiClass[].class);
            for (WinApiClass clazz : winApiClasses) {
                service.saveOrUpdate(clazz);
            }
        }
    }
}
