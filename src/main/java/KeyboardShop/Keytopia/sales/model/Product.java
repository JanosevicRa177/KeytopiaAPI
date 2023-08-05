package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @Column(name="id_product")
    private UUID id;
    @Column(name="sold_date")
    private Date soldDate;
    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;
    @Column(name="product_price")
    private double price;
    @ManyToOne
    @JoinColumn(name="part_name",nullable = false)
    private Part part;
    @ManyToOne
    @JoinColumn(name="keyboard_name",nullable = false)
    private Keyboard keyboard;
}
