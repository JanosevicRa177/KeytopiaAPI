package KeyboardShop.Keytopia.sales.Dto;

import KeyboardShop.Keytopia.parts.dto.part.PartDto;
import KeyboardShop.Keytopia.parts.dto.part.PartItemDto;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private String id;
    private PartItemDto part;
    public ProductDto(Product product){
        this.id = product.getId().toString();
        if(product.getPart() != null) 
            this.part = new PartItemDto(product.getPart());
        else if(product.getKeyboard() != null)
            this.part = new PartItemDto(product.getKeyboard());
        else throw new PartNotFoundException("Can't get you your procurements!");
    }
}
