package KeyboardShop.Keytopia.utils.excentions.utilExceptions;

import KeyboardShop.Keytopia.utils.excentions.BaseException;
import org.springframework.http.HttpStatus;


public class FileUploadException extends BaseException {
    public FileUploadException(){
        super("Failed to upload image", HttpStatus.CONFLICT);
    }
}
