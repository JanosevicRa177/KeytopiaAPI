package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.auth.model.Buyer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
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
    private List<Service> services;
    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name="idDeliveryService", nullable=false)
    private DeliveryService deliveryService;
}