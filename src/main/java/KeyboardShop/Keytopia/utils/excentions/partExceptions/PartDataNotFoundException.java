package KeyboardShop.Keytopia.utils.excentions.partExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartDataNotFoundException extends BaseException {
    public PartDataNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
