package KeyboardShop.Keytopia.auth.dto;

import KeyboardShop.Keytopia.auth.model.Role;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.shared.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Role role;
    private Address address;
    public UserDto(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.email = user.getEmail();
    }
}
