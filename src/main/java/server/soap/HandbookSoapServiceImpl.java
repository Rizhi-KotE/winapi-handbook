package server.soap;

import common.service.HandbookSoapService;
import lombok.Setter;
import model.WinApiClass;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "common.service.HandbookSoapService")
public class HandbookSoapServiceImpl implements HandbookSoapService {

    @Override
    public WinApiClass getTopic(long id) {
        return null;
    }

    @Override
    public WinApiClass[] findTopics(String keyword) {
        return new WinApiClass[0];
    }

    @Override
    public long createTopic(WinApiClass topic) {
        return 0;
    }

    @Override
    public void updateTopic(WinApiClass topic) {

    }

    @Override
    public void removeTopic(long id) {

    }
}
