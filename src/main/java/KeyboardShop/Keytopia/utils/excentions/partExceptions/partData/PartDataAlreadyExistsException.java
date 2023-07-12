package KeyboardShop.Keytopia.utils.excentions.partExceptions.partData;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartDataAlreadyExistsException extends BaseException {
    public PartDataAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
