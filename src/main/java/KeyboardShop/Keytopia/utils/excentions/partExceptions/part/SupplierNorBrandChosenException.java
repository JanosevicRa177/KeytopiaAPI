package KeyboardShop.Keytopia.utils.excentions.partExceptions.part;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class SupplierNorBrandChosenException extends BaseException {
    public SupplierNorBrandChosenException(){
        super("You haven't chosen neither brand or supplier, choose at least one", HttpStatus.BAD_REQUEST);
    }
}
