package KeyboardShop.Keytopia.utils.excentions.warehouseExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class WarehouseEntityAlreadyExistsException extends BaseException {
    public WarehouseEntityAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
