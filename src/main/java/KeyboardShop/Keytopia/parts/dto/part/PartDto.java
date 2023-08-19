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
public class PartDto {
    private String name;
    private Integer quantity;
    private Double price;
    private PriceWeight priceWeight;
    private String brand;
    private String supplier;
    private String imageUrl;
    public PartDto(Part part,boolean isAdmin){
        this.name = part.getName();
        if(isAdmin)
            this.quantity = part.getQuantity();
        this.price = part.getPrice();
        this.priceWeight = part.getPriceWeight();
        this.imageUrl = part.getImageUrl();
        if(part.getSupplier() != null) this.setSupplier(part.getSupplier().getName());
        if(part.getBrand() != null) this.setBrand(part.getBrand().getName());
    }
}
