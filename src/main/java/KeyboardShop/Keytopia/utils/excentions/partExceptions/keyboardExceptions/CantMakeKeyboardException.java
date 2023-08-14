package KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class CantMakeKeyboardException extends BaseException {
    public CantMakeKeyboardException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
