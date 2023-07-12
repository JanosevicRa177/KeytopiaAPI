package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
public class PartDto {
    private final String name;
    private final int quantity;
    private final double price;
    private final PriceWeight priceWeight;
    private final String brandName;
    public PartDto(Part part){
        this.brandName = part.getBrand().getName();
        this.name = part.getName();
        this.quantity = part.getQuantity();
        this.price = part.getPrice();
        this.priceWeight = part.getPriceWeight();
    }
}
