package by.iba.controller;

import by.iba.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // TODO add MethodArgumentNotValidException handler
    // TODO remove such general handler
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processValidationError(IllegalArgumentException e) {
        log.info("Returning HTTP 400 Bad Request", e);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> processError(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return
                new ResponseEntity<>(
                        new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "server_error",
                                e.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
