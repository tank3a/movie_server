package dbclass.movie.exceptionHandler;

public class DateErrorException extends RuntimeException {
    public DateErrorException(String message) {
        super(message);
    }
}
