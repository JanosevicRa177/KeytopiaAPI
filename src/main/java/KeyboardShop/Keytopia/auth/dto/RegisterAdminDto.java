package KeyboardShop.Keytopia.auth.dto;

import KeyboardShop.Keytopia.shared.model.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class RegisterAdminDto {
    private String name;
    private String surname;
    private String phone;
    @Email
    private String email;
    private Address address;
}
