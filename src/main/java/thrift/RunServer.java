package thrift;

import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class RunServer {
    public static void main(String[] args) {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("serverContext.groovy");

    }
}
