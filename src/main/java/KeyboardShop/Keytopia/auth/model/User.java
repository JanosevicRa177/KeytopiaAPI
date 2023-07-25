package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.auth.dto.RegisterAdminDto;
import KeyboardShop.Keytopia.auth.dto.RegisterBuyerDto;
import KeyboardShop.Keytopia.auth.dto.UserChangeDto;
import KeyboardShop.Keytopia.shared.model.Address;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "user_entity")
public class User {
    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_name")
    private String name;
    @Column(name="user_surname")
    private String surname;
    @Column(name="user_phone")
    private String phone;
    @Column(name="Role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="email",unique = true)
    private String email;
    @Column(name="user_password")
    private String password;

    @Column(name="activated")
    private boolean isActivated;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private Address address;
    
    public User(RegisterBuyerDto registerDto){
        this.name = registerDto.getName();
        this.surname = registerDto.getSurname();
        this.email = registerDto.getEmail();
        this.password = registerDto.getPassword();
        this.address = registerDto.getAddress();
        this.phone = registerDto.getPhone();
    }
    public User(RegisterAdminDto registerDto){
        this.name = registerDto.getName();
        this.surname = registerDto.getSurname();
        this.email = registerDto.getEmail();
        this.address = registerDto.getAddress();
        this.phone = registerDto.getPhone();
    }
    public void updateUser(UserChangeDto userChangeDto){
        this.name = userChangeDto.getName();
        this.surname = userChangeDto.getSurname();
        this.address = userChangeDto.getAddress();
        this.phone = userChangeDto.getPhone();
    }
}
