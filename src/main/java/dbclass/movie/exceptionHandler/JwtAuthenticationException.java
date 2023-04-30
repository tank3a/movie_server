package dbclass.movie.exceptionHandler;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
