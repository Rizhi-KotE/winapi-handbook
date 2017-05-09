package client.service;

import common.service.HandbookSoapService;
import lombok.Setter;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

// такой эксепшн возникнет при работе с объектом URL
// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дотянуться до тега common.service в нем

public class SoupHandbookServiceFactory {

    @Setter
    String url;

    @Setter
    String localPart;

    @Setter
    String namespaceUri;

    private HandbookSoapService hello;

    HandbookSoapService createService() throws MalformedURLException {
        URL url = new URL(this.url);

        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname = new QName(namespaceUri, localPart);

        // Теперь мы можем дотянуться до тега common.service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса

        hello = service.getPort(HandbookSoapService.class);
        return hello;
    }
}