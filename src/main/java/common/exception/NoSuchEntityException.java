package common.exception;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException(Object topic) {
        super(topic.toString());
    }
}
