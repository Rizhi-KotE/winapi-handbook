package client.service;

import common.service.WinApiHandbookService;
import common.service.HandbookSoapService;
import model.WinApiClass;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class HandbookSoapAdapter implements WinApiHandbookService {

    HandbookSoapService soapService;

    public HandbookSoapAdapter(HandbookSoapService soapService) {
        this.soapService = soapService;
    }

    public void setup() {
        System.out.println("Run soap client");
    }

    @Override
    public WinApiClass getWinApiClass(long id) {
        return soapService.getTopic(id);
    }

    @Override
    public List<WinApiClass> findClasses(String keyword) {
        return Arrays.asList(soapService.findTopics(keyword));
    }

    @Override
    public long createWinApiClass(WinApiClass topic) {
        return soapService.createTopic(topic);
    }

    @Override
    public void updateTopic(WinApiClass topic) {
        soapService.updateTopic(topic);
    }

    @Override
    public void removeTopic(long id) {
        soapService.removeTopic(id);
    }
}
