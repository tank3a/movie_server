package dbclass.movie.exceptionHandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessTokenExpireException.class)
    public ResponseEntity<ErrorResponse> refreshRequest(AccessTokenExpireException exception) {
        log.warn("refresh token request");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage())
                .errorCode("ACCESS_TOKEN_EXPIRED")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
