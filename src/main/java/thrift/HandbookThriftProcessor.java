package thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class HandbookThriftProcessor {

    @Setter
    int port;
    @Setter
    HibernateHandbookThriftService handler;
    private HandbookThrift.Processor processor;


    void setup() {
        try {
            processor = new HandbookThrift.Processor(handler);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void run() {
        new Thread(this::simple).start();
    }

    private void simple() {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
