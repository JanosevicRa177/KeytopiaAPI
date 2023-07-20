package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.shared.model.Address;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "UserEntity")
public class User {
    @Id
    @Column(name="idUser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="UserName")
    private String name;
    @Column(name="UserSurname")
    private String surname;
    @Column(name="UserPhone")
    private String phone;
    @Column(name="Role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="Email",unique = true)
    private String email;
    @Column(name="UserPassword")
    private String password;

    @Column(name="Activated")
    private boolean isActivated;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idAddress", referencedColumnName = "idAddress")
    private Address address;
    
    public User(RegisterDto registerDto){
        this.name = registerDto.getName();
        this.surname = registerDto.getSurname();
        this.email = registerDto.getEmail();
        this.password = registerDto.getPassword();
        this.address = registerDto.getAddress();
        this.phone = registerDto.getPhone();
    }
}
