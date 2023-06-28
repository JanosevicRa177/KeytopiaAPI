package KeyboardShop.Keytopia.utils.excentions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    protected HttpStatus status;
    protected BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
