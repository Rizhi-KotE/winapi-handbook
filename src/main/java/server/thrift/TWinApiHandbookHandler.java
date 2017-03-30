package server.thrift;

import model.TWinApiClass;
import org.apache.thrift.TException;

import java.util.List;

public class TWinApiHandbookHandler implements TWinApiHandbookService.Iface {
    @Override
    public TWinApiClass getClass(long id) throws TException {
        return null;
    }

    @Override
    public List<TWinApiClass> findClass(String keyword) throws TException {
        return null;
    }

    @Override
    public List<TWinApiClass> findFunction(String keyword) throws TException {
        return null;
    }

    @Override
    public long createClass(TWinApiClass topic) throws TException {
        return 0;
    }

    @Override
    public void updateClass(TWinApiClass topic) throws TNoSuchEntityException, TException {

    }

    @Override
    public void removeClass(long id) throws TException {

    }
}
