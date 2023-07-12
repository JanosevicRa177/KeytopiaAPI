package KeyboardShop.Keytopia.utils.excentions.partExceptions.part;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartAlreadyExistsException extends BaseException {
    public PartAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
