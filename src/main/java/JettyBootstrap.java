import org.springframework.context.support.GenericGroovyApplicationContext;
import server.soap.HandbookSoapPublisher;

public class JettyBootstrap {
    public static void main(String[] args) throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("serverContext.groovy");
        HandbookSoapPublisher bean = context.getBean(HandbookSoapPublisher.class);
        bean.publish();
//        ThriftServer bean = context.getBean(ThriftServer.class);
//        bean.run();
    }
}

