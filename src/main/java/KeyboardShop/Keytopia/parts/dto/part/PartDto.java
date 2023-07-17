package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {
    private String name;
    private int quantity;
    private double price;
    private PriceWeight priceWeight;
    private String brandName;
    private String imageUrl;
    public PartDto(Part part){
        this.brandName = part.getBrand().getName();
        this.name = part.getName();
        this.quantity = part.getQuantity();
        this.price = part.getPrice();
        this.priceWeight = part.getPriceWeight();
        this.imageUrl = part.getImageUrl();
    }
}
