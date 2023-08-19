package KeyboardShop.Keytopia.utils.excentions.authExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class BuyerNotFoundException extends BaseException {
    public BuyerNotFoundException(){
        super("We can't find you!", HttpStatus.NOT_FOUND);
    }
}
