package KeyboardShop.Keytopia.utils.excentions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends  BaseException{
    public UserAlreadyExistsException() {
        super("User with this email already exists!", HttpStatus.BAD_REQUEST);
    }
}
