package KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardCantBeDeletedException extends BaseException {
    public KeyboardCantBeDeletedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
