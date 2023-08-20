package KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardAlreadyCommercializedException extends BaseException {
    public KeyboardAlreadyCommercializedException(){
        super("Keyboard already commercialized!", HttpStatus.BAD_REQUEST);
    }
}
