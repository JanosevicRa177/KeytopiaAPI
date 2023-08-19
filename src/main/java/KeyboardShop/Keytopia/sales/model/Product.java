package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @Column(name="id_product")
    private UUID id;
    @Column(name="sold_date")
    private LocalDate soldDate;
    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;
    @Column(name="product_price")
    private double price;
    @ManyToOne
    @JoinColumn(name="part_name")
    private Part part;
    @ManyToOne
    @JoinColumn(name="keyboard_name")
    private Keyboard keyboard;
    public Product(Part part,Order order){
        this.part = part;
        this.price = part.getPrice();
        this.soldDate = LocalDate.now();
        this.id = UUID.randomUUID();
        this.order = order;
    }
    public Product(Keyboard keyboard,Order order){
        this.keyboard = keyboard;
        this.price = keyboard.getPrice();
        this.soldDate = LocalDate.now();
        this.id = UUID.randomUUID();
        this.order = order;
    }
}
