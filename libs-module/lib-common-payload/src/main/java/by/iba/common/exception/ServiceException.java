package by.iba.common.exception;


public class ServiceException extends AbstractException {

    public ServiceException(Integer httpCode, String error, String errorDescription) {
        super(httpCode, error, errorDescription);
    }
}
