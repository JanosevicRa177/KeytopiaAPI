package KeyboardShop.Keytopia.sales.model;

import KeyboardShop.Keytopia.sales.Dto.DeliveryServiceDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "delivery_service")
public class DeliveryService {
    @Id
    @Column(name="delivery_service_name")
    private String name;
    @Column(name="delivery_service_phone")
    private String phone;
    @OneToMany(mappedBy = "deliveryService")
    private List<Order> orders;
    public DeliveryService(DeliveryServiceDto deliveryServiceDto){
        this.name = deliveryServiceDto.getName();
        this.phone = deliveryServiceDto.getPhone();
        this.orders = new ArrayList<>();
    }

    public void update(DeliveryServiceDto deliveryServiceDto){
        this.name = deliveryServiceDto.getName();
        this.phone = deliveryServiceDto.getPhone();
    }
}
