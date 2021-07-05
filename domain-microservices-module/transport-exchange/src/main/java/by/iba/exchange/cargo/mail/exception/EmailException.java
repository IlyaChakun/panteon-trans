package by.iba.exchange.cargo.mail.exception;

import by.iba.exchange.common.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class EmailException extends AbstractException {

    public EmailException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "invalid email", errorDescription);
    }
}
