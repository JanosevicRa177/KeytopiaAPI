package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.sales.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
