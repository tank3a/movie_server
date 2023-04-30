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

    @org.springframework.web.bind.annotation.ExceptionHandler(LoginFailureException.class)
    public ResponseEntity<ErrorResponse> loginFailure(AccessTokenExpireException exception) {
        log.warn("invalid login request");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage())
                .errorCode("LOGIN_FAILURE")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorResponse> duplicateUserId(DuplicateUserException exception) {
        log.warn("duplicate request: " + exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(exception.getMessage())
                .errorCode("USER_DATA_CONFLICT")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorResponse> jwtError(JwtAuthenticationException exception) {
        log.warn("jwt token error: " + exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(exception.getMessage())
                .errorCode("JWT_ERROR")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotExistException.class)
    public ResponseEntity<ErrorResponse> notExistError(NotExistException exception) {
        log.warn("user not exist: " + exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .errorCode("USER_NOT_EXIST_ERROR")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<ErrorResponse> invalidAccess(InvalidAccessException exception) {
        log.warn("invalid access: " + exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(exception.getMessage())
                .errorCode("ACCESS_INVALID")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
