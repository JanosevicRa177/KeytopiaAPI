package KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardPartNotPresentException extends BaseException {
    public KeyboardPartNotPresentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
