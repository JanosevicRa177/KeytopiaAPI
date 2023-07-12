package KeyboardShop.Keytopia.utils.excentions.partExceptions.part;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class PartNotFoundException extends BaseException {
    public PartNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
