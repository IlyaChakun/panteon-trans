package by.iba.controller;

import by.iba.exception.AbstractException;
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


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processValidationError(IllegalArgumentException e) {
        log.info("Returning HTTP 400 Bad Request", e);
    }


    @ExceptionHandler({AbstractException.class})
    public ResponseEntity<ErrorMessage> handleException(AbstractException ex) {
        /*

         */
        ex.printStackTrace();
        return
                new ResponseEntity<>(
                        new ErrorMessage(
                                ex.getCode(),
                                ex.getError(),
                                ex.getErrorDescription()
                        ),
                        HttpStatus.valueOf(ex.getCode()));
    }
}
