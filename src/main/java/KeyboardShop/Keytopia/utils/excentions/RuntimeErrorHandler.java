package KeyboardShop.Keytopia.utils.excentions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeErrorHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> notFoundExceptionHandler(BaseException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getStatus()), exception.getStatus());
    }
}
