package server.soap;

import common.service.HandbookSoapService;
import lombok.Setter;

import javax.xml.ws.Endpoint;

public class HandbookSoapPublisher {
    @Setter
    String url;

    @Setter
    HandbookSoapService service;

    public void publish() {
        Endpoint.publish(url, service);
    }
}