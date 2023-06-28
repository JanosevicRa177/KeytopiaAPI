package KeyboardShop.Keytopia.auth.dto;

import KeyboardShop.Keytopia.shared.model.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterDto {
    private String name;
    private String surname;
    private String phone;
    @Email
    private String email;
    @Pattern(regexp = "^[0-9a-zA-Z!?]{9}[0-9a-zA-Z!?]*")
    private String password;
    @Pattern(regexp = "^[0-9a-zA-Z!?]{9}[0-9a-zA-Z!?]*")
    private String confirmPassword;
    private Address address; 
}
