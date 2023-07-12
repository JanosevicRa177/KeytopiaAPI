package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class ConfirmPasswordNotEqualException extends BaseException {
    public ConfirmPasswordNotEqualException() {
        super("Password and confirm password not equals", HttpStatus.BAD_REQUEST);
    }
}
