package KeyboardShop.Keytopia.sales.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "delivery_service")
public class DeliveryService {
    @Id
    @Column(name="id_delivery_service")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="delivery_service_name")
    private String name;
    @Column(name="delivery_service_phone")
    private String phone;
    @OneToMany(mappedBy = "deliveryService")
    private List<Order> orders;
}
