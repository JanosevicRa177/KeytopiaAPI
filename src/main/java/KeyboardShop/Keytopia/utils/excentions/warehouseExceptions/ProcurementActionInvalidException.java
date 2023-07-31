package KeyboardShop.Keytopia.utils.excentions.warehouseExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class ProcurementActionInvalidException extends BaseException {
    public ProcurementActionInvalidException(String message){
        super(message, HttpStatus.CONFLICT);
    }
}
