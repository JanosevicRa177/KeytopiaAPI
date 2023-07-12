package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PartDto {
    private String name;
    private int quantity;
    private double price;
    private PriceWeight priceWeight;
    private String brandName;
    public PartDto(Part part){
        this.brandName = part.getBrand().getName();
        this.name = part.getName();
        this.quantity = part.getQuantity();
        this.price = part.getPrice();
        this.priceWeight = part.getPriceWeight();
    }
}
