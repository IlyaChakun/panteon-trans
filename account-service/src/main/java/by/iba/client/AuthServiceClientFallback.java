package by.iba.client;

import by.iba.common.exception.ServiceException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthServiceClientFallback implements FallbackFactory<AuthServiceClient> {


    @Override
    public AuthServiceClient create(Throwable cause) {
        int httpStatus = cause instanceof FeignException ? (((FeignException) cause).status()) : 0;

        log.info("httpStatus={}", httpStatus);
        log.info(cause.getMessage());
        log.info(cause.toString());

        throw new ServiceException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "account_service_fetch_error",
                "Can`t complete fetch! Try again later"
        );
    }
}
