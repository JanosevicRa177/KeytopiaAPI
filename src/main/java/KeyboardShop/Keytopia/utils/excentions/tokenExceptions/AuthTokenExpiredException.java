package KeyboardShop.Keytopia.utils.excentions.tokenExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class AuthTokenExpiredException extends BaseException {
    public AuthTokenExpiredException() {
        super("Authentication expired", HttpStatus.UNAUTHORIZED);
    }
}
