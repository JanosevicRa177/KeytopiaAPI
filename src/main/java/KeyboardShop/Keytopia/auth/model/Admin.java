package KeyboardShop.Keytopia.auth.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "Admin")
public class Admin extends User {
}
