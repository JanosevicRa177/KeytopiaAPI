package KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardAlreadyExistsException extends BaseException {
    public KeyboardAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
