package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {
    private String name;
    private Integer quantity;
    private Double price;
    private PriceWeight priceWeight;
    private String brand;
    private String imageUrl;
    public PartDto(Part part){
        this.brand = part.getBrand().getName();
        this.name = part.getName();
        this.quantity = part.getQuantity();
        this.price = part.getPrice();
        this.priceWeight = part.getPriceWeight();
        this.imageUrl = part.getImageUrl();
    }
}
