package by.iba.common.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class BaseErrorHandlerController {


    private final MessageSource messageSource;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processValidationError(IllegalArgumentException e) {
        log.info("Returning HTTP 400 Bad Request", e);
    }


    @ExceptionHandler({AbstractException.class})
    public ResponseEntity<ErrorMessage> handleAbstractException(AbstractException ex, Locale locale) {
        /* Handles AbstractException exceptions. */
        log.info("in handleAbstractException");
        log.info("local={}", locale);

        final String localizedMessage = messageSource.getMessage(ex.getError(), (Object[]) null, locale);

        log.info("localizedMessage={}", localizedMessage);
        return
                new ResponseEntity<>(
                        new ErrorMessage(
                                ex.getHttpCode(),
                                ex.getError(),
                                localizedMessage
                        ),
                        HttpStatus.valueOf(ex.getHttpCode())
                );
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
