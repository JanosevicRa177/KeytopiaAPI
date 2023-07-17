package KeyboardShop.Keytopia.auth.model;

import KeyboardShop.Keytopia.auth.dto.RegisterDto;
import KeyboardShop.Keytopia.sales.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Buyer")
public class Buyer extends User {
    @OneToMany(mappedBy = "buyer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    public Buyer(RegisterDto registerDto){
        super(registerDto);
        this.setRole(Role.BUYER);
    }
}
