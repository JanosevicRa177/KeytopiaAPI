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
@Entity(name = "order_entity")
public class Order {
    @Id
    @Column(name="id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_date")
    private Date date;
    @Column(name="order_deadline")
    private Date deadline;
    @ManyToMany
    @JoinTable(name="order_service",
            joinColumns=@JoinColumn(name="id_order"),
            inverseJoinColumns=@JoinColumn(name="id_service")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Service> services;
    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name="idDeliveryService", nullable=false)
    private DeliveryService deliveryService;
    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
