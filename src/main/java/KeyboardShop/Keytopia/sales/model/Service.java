package KeyboardShop.Keytopia.sales.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Service")
public class Service {
    @Id
    @Column(name="idService")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ServiceName")
    private String name;
    @ManyToMany
    private List<Order> orders;
}
