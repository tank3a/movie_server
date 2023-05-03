package dbclass.movie.exceptionHandler;

import lombok.Getter;

@Getter
public class MovieNotFoundException extends RuntimeException {
    private final String type;
    public MovieNotFoundException(String message, String type) {
        super(message);
        this.type = type;
    }
}
