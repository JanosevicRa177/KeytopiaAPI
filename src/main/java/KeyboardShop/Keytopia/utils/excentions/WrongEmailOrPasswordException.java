package KeyboardShop.Keytopia.utils.excentions;

import org.springframework.http.HttpStatus;

public class WrongEmailOrPasswordException extends BaseException{
    public WrongEmailOrPasswordException() {
        super("Wrong email or password", HttpStatus.BAD_REQUEST);
    }
}
