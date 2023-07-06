package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotActivatedException extends BaseException {
    public UserNotActivatedException(){
        super("Your account is not activated!", HttpStatus.UNAUTHORIZED);
    }
}
