package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @Column(name="id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="sold_date")
    private Date soldDate;
    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;
    @Column(name="product_price")
    private double price;
    @ManyToOne
    @JoinColumn(name="id_part",nullable = false)
    private Part part;
}
