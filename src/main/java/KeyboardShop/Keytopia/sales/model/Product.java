package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.parts.model.Part;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
public class Product {
    @Id
    @Column(name="idProduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="SoldDate")
    private Date soldDate;
    @ManyToOne
    @JoinColumn(name="idOrder")
    private Order order;
    @ManyToOne
    @JoinColumn(name="idPart",nullable = false)
    private Part part;
}
