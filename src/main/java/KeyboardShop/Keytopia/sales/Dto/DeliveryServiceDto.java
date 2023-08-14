package KeyboardShop.Keytopia.sales.Dto;

import KeyboardShop.Keytopia.sales.model.DeliveryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryServiceDto {
    private String name;
    private String phone;
    
    public DeliveryServiceDto(DeliveryService deliveryService){
        this.name = deliveryService.getName();
        this.phone = deliveryService.getPhone();
    }
}
