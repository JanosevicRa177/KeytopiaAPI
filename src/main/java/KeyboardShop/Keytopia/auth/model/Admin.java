package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.auth.dto.RegisterAdminDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "Admin")
public class Admin extends User {
    public Admin(RegisterAdminDto registerDto){
        super(registerDto);
        this.setRole(Role.ADMIN);
        this.setActivated(true);
    }
}
