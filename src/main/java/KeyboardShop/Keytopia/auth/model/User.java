package KeyboardShop.Keytopia.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
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
    private Role role;
    @Column(name="Email",unique = true)
    private String email;
}
