package server.thrift;

import common.service.WinApiHibernateHandbookService;
import lombok.Setter;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServlet;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class HandbookTServletFactory {

    @Setter
    TWinApiHandbookHandler handler;

    public TServlet createHandbookTServlet(){
        return new TServlet(new TWinApiHandbookService.Processor(handler), new TBinaryProtocol.Factory());
    }
}
