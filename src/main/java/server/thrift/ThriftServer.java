package server.thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class ThriftServer {
    @Setter
    HibernateHandbookThriftService handler;
    @Setter
    int port;

    public void run() {
        try {
            HandbookThrift.Processor<HibernateHandbookThriftService> processor = new HandbookThrift.Processor<>(handler);

            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            TServer server = new TNonblockingServer(
                    new TNonblockingServer.Args(serverTransport)
                    .processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
