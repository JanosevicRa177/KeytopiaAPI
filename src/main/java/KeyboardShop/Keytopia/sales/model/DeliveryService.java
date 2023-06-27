package KeyboardShop.Keytopia.sales.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
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
