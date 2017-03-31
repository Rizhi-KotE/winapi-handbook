package server.thrift;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.service.WinApiClassRepository;
import common.service.WinApiHibernateHandbookService;
import lombok.Setter;
import model.WinApiClass;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rizhi-kote on 14.03.17.
 */
public class InitializeBase {

    @Setter
    WinApiHibernateHandbookService repository;

    @Setter
    List<String> content;

    public void setup() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (String fileName : content) {
            WinApiClass[] winApiClasses = objectMapper.readValue(new ClassPathResource(fileName).getURL(), WinApiClass[].class);
            for (WinApiClass clazz : winApiClasses) {
                long winApiClass = repository.createWinApiClass(clazz);
                WinApiClass winApiClass1 = repository.getWinApiClass(winApiClass);
                repository.createWinApiClass(winApiClass1);
            }
        }
    }
}
