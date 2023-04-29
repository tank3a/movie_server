package dbclass.movie.exceptionHandler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorResponse {

    private HttpStatus status;
    private String errorCode;
    private String message;
}
