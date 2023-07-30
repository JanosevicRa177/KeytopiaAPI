package KeyboardShop.Keytopia.utils.excentions.warehouseExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;


public class ProcurementInvalidException extends BaseException {
    public ProcurementInvalidException(String parts){
        super("Problem creating procurement for parts: " + parts + ". No supported supplier", HttpStatus.BAD_REQUEST);
    }
}
