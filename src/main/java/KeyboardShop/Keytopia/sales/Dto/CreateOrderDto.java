package KeyboardShop.Keytopia.sales.Dto;

import KeyboardShop.Keytopia.warehouse.dto.PartWithQuantityDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {
    private List<PartWithQuantityDto> parts;
    private String deliveryServiceName;
}
