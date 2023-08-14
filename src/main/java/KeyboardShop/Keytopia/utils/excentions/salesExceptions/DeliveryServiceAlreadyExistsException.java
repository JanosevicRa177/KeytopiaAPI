package KeyboardShop.Keytopia.utils.excentions.salesExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class DeliveryServiceAlreadyExistsException extends BaseException {
    public DeliveryServiceAlreadyExistsException(){
        super("Delivery with this name already exists.", HttpStatus.BAD_REQUEST);
    }
}
