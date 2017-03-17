import org.springframework.context.support.GenericGroovyApplicationContext;
import server.soap.HandbookSoapPublisher;
import server.thrift.ThriftServer;

public class StartServer {
    public static void main(String[] args) throws Exception {
        GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("serverContext.groovy");
        HandbookSoapPublisher soap = context.getBean(HandbookSoapPublisher.class);
        soap.publish();
        ThriftServer thrift = context.getBean(ThriftServer.class);
        thrift.run();
    }
}

