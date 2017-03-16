package common.service;// это аннотации, т.е. способ отметить наши классы и методы,
// как связанные с веб-сервисной технологией

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

// говорим, что наш интерфейс будет работать как веб-сервис
@WebService(wsdlLocation = "wsdl/handbook.wsdl")
// говорим, что веб-сервис будет использоваться для вызова методов
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HandbookSoapService {
    @WebMethod
    Topic getTopic(long id);

    @WebMethod
    Topic[] findTopics(String keyword);

    @WebMethod
    long createTopic(Topic topic);

    @WebMethod
    void updateTopic(Topic topic);

    @WebMethod
    void removeTopic(long id);
}