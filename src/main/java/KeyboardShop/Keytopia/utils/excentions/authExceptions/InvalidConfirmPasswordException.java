package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidConfirmPasswordException extends BaseException {
    public InvalidConfirmPasswordException() {
        super("Password and confirm password are not matching!", HttpStatus.BAD_REQUEST);
    }
}
