package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.auth.model.Buyer;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "order_entity")
public class Order {
    @Id
    @Column(name="id_order")
    private UUID id;
    @Column(name="order_date", nullable=false)
    private LocalDate date;
    @Column(name="order_deadline", nullable=false)
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private Buyer buyer;
    @Column(name="order_price")
    private double price;
    @ManyToOne
    @JoinColumn(name="delivery_service_name", nullable=false)
    private DeliveryService deliveryService;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
    public Order(Buyer buyer, DeliveryService deliveryService){
        this.id = UUID.randomUUID();
        this.buyer = buyer;
        this.deliveryService = deliveryService;
        this.products = new ArrayList<>();
        this.date = LocalDate.now();
        this.deadline =  LocalDate.now().plusWeeks(1);
    }
    public void addProductToOrder(Product product){
        products.add(product);
    }
    public void calculatePrice(){
        this.price = products.stream().mapToDouble(Product::getPrice).sum();
    }
}
