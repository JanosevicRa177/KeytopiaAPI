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
    @Column(name="idDeliveryService")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="DeliveryServiceName")
    private String name;
    @Column(name="DeliveryServicePhone")
    private String phone;
    @OneToMany
    private List<Order> orders;
}
