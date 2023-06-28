package KeyboardShop.Keytopia.utils.excentions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    protected HttpStatus status;
    protected BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
