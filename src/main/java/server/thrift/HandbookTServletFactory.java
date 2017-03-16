package server.thrift;

import lombok.Setter;
import model.HandbookThrift;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class HandbookTServletFactory {

    @Setter
    HibernateHandbookThriftService handler;

    public TServlet createHandbookTServlet(){
        return new TServlet(new HandbookThrift.Processor<>(handler), new TBinaryProtocol.Factory());
    }
}
