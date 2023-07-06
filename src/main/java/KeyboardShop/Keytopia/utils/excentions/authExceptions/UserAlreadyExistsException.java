package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException() {
        super("User with this email already exists!", HttpStatus.BAD_REQUEST);
    }
}
