package KeyboardShop.Keytopia.utils.excentions;

import org.springframework.http.HttpStatus;

public class UnsupportedPartTypeException extends BaseException{
    public UnsupportedPartTypeException(){
        super("Part type you are looking for does not exists", HttpStatus.NOT_FOUND);
    }
}
