package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.auth.model.Buyer;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderEntity")
public class Order {
    @Id
    @Column(name="idOrder")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="OrderDate")
    private Date date;
    @Column(name="OrderDeadline")
    private Date deadline;
    @ManyToMany
    @JoinTable(name="order_service",
            joinColumns=@JoinColumn(name="idOrder"),
            inverseJoinColumns=@JoinColumn(name="idService")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Service> services;
    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name="idDeliveryService", nullable=false)
    private DeliveryService deliveryService;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
