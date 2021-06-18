package by.iba.controller;

import by.iba.common.exception.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {


    @ExceptionHandler({FeignException.class})
    public ResponseEntity<ErrorMessage> handleFeignStatusException(FeignException ex) throws JsonProcessingException {

        final ErrorMessage errorMessage = new ObjectMapper().readValue(ex.contentUTF8(), ErrorMessage.class);

        log.error(errorMessage.toString());

        return
                new ResponseEntity<>(
                        errorMessage,
                        HttpStatus.valueOf(errorMessage.getHttpCode())
                );
    }


}
