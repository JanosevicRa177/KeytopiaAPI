package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.sales.model.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Buyer")
public class Buyer extends User {
    @OneToMany
    private List<Order> orders;
}
