package by.iba.security.mail.exception;

import by.iba.common.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends AbstractException {


    public InvalidEmailException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "invalid email", errorDescription);
    }
}
