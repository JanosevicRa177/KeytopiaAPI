package KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class KeyboardNotFoundException extends BaseException {
    public KeyboardNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}

