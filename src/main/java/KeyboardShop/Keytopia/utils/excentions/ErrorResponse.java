package KeyboardShop.Keytopia.utils.excentions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    public String message;
    public HttpStatus status;
}
