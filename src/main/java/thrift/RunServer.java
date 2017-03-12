package thrift;

import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class RunServer {
    public static void main(String[] args) {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("serverContext.groovy");
        HandbookThriftProcessor bean = context.getBean(HandbookThriftProcessor.class);
        bean.run();
    }
}
