package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class WrongEmailOrPasswordException extends BaseException {
    public WrongEmailOrPasswordException() {
        super("Wrong email or password", HttpStatus.BAD_REQUEST);
    }
}
