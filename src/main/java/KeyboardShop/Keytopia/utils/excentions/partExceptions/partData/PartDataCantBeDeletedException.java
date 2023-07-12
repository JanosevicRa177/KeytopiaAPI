package KeyboardShop.Keytopia.utils.excentions.partExceptions.partData;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartDataCantBeDeletedException extends BaseException {
    public PartDataCantBeDeletedException(String message){
        super(message, HttpStatus.CONFLICT);
    }
}
