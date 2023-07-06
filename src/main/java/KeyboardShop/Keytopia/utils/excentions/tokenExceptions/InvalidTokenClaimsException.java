package KeyboardShop.Keytopia.utils.excentions.tokenExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidTokenClaimsException extends BaseException {
    public InvalidTokenClaimsException() {
        super("Invalid token claims, can't find user!", HttpStatus.UNAUTHORIZED);
    }
}
