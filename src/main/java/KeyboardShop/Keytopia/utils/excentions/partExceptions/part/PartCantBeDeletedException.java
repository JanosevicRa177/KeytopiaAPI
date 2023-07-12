package KeyboardShop.Keytopia.utils.excentions.partExceptions.part;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartCantBeDeletedException extends BaseException {
    public PartCantBeDeletedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
