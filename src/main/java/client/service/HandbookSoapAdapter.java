package client.service;

import common.service.HandbookService;
import common.service.HandbookSoapService;
import common.service.Topic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rizhi-kote on 16.03.17.
 */
public class HandbookSoapAdapter implements HandbookService {

    HandbookSoapService soapService;

    public HandbookSoapAdapter(HandbookSoapService soapService) {
        this.soapService = soapService;
    }

    @Override
    public Topic getTopic(long id) {
        return soapService.getTopic(id);
    }

    @Override
    public List<Topic> findTopics(String keyword) {
        return Arrays.asList(soapService.findTopics(keyword));
    }

    @Override
    public long createTopic(Topic topic) {
        return soapService.createTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        soapService.updateTopic(topic);
    }

    @Override
    public void removeTopic(long id) {
        soapService.removeTopic(id);
    }
}
