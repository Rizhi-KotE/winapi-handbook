package common.exception;

public class WinApiNoContentException extends HandbookException {
    public WinApiNoContentException() {
    }

    public WinApiNoContentException(String message) {
        super(message);
    }

    public WinApiNoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public WinApiNoContentException(Throwable cause) {
        super(cause);
    }

    public WinApiNoContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
