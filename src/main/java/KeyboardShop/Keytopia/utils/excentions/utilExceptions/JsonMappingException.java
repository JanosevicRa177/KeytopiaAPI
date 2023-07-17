package KeyboardShop.Keytopia.utils.excentions.utilExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;

public class JsonMappingException extends BaseException {
    public JsonMappingException(){
        super("Problem sending data, contact admin for more information!", HttpStatus.BAD_REQUEST);   
    }
}
