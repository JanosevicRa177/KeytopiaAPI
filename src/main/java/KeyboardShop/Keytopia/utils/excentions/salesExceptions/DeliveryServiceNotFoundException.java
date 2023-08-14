package KeyboardShop.Keytopia.utils.excentions.salesExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class DeliveryServiceNotFoundException extends BaseException {
    public DeliveryServiceNotFoundException(){
        super("Delivery service not found!", HttpStatus.NOT_FOUND);
    }
}
