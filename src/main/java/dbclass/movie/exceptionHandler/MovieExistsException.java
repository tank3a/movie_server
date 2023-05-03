package dbclass.movie.exceptionHandler;

import lombok.Getter;

@Getter
public class MovieExistsException extends RuntimeException {

    private final String type;
    public MovieExistsException(String message, String type) {
        super(message);
        this.type = type;
    }
}
