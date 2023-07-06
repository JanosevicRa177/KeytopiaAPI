package KeyboardShop.Keytopia.utils.excentions.tokenExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
