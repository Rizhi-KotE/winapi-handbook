package thrift;

import model.HandbookThrift;
import org.apache.thrift.TException;

import java.util.Map;

public class HibernateHandbookThriftHandler implements HandbookThrift.Iface {

    @Override
    public String getContent(long id) throws TException {
        return null;
    }

    @Override
    public Map<Long, String> findTopicsHeaders(String keyword) throws TException {
        return null;
    }

    @Override
    public long createTopic(String name, String content) throws TException {
        return 0;
    }

    @Override
    public void updateTopic(long id, String content) throws TException {

    }

    @Override
    public void removeTopic(long id) throws TException {

    }
}
