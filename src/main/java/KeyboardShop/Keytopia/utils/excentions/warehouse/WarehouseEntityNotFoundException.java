package KeyboardShop.Keytopia.utils.excentions.warehouse;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class WarehouseEntityNotFoundException  extends BaseException {
    public WarehouseEntityNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
