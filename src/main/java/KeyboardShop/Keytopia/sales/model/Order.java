package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.auth.model.Buyer;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
    @Column(name="order_date")
    private Date date;
    @Column(name="order_deadline")
    private Date deadline;
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name="delivery_service_name")
    private DeliveryService deliveryService;
    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
