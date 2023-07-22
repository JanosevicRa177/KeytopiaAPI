package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends BaseException {
    public IncorrectPasswordException() {
        super("Incorrect password!", HttpStatus.BAD_REQUEST);
    }
}
