package KeyboardShop.Keytopia.utils.excentions.warehouseExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class ProcurementDeadlineNotYetExpiredException extends BaseException {
    public ProcurementDeadlineNotYetExpiredException(){
        super("Supplier cant be penalized because deadline didn't expire", HttpStatus.CONFLICT);
    }
}
