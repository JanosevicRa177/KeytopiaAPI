package KeyboardShop.Keytopia.utils.excentions.tokenExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class TokenInvalidException extends BaseException {
    public TokenInvalidException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
