package KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardNameNotPresentException extends BaseException {
    public KeyboardNameNotPresentException() {
        super("You should add keyboard name!", HttpStatus.BAD_REQUEST);
    }
}
