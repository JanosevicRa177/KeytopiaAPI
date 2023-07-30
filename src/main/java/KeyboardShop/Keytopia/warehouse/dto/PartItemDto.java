package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartItemDto {
    private String name;
    private Integer quantity;
    public PartItemDto(ProcurementPart part){
        this.name = part.getPart().getName();
        this.quantity = part.getQuantity();
    }
}
