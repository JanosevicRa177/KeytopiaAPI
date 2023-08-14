package KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardWithNameAlreadyExistsException extends BaseException {
    public KeyboardWithNameAlreadyExistsException() {
        super("Keyboard with this name already exists", HttpStatus.CONFLICT);
    }
}
