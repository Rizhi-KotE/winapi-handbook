package common.service;

public class ConverterUtils {

    public static Topic convert(model.Topic topic){
        return new Topic(topic.getId(), topic.getContent(), topic.getHeader());
    }

    public static model.Topic convert(Topic topic){
        return new model.Topic(topic.getId(), topic.getContent(), topic.getHeader());
    }
}
