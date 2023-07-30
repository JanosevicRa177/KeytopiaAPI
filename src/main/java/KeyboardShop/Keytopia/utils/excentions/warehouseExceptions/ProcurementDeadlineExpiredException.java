package KeyboardShop.Keytopia.utils.excentions.warehouseExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class ProcurementDeadlineExpiredException extends BaseException {
    public ProcurementDeadlineExpiredException(){
        super("Deadline for procurement expired.", HttpStatus.CONFLICT);
    }
}
