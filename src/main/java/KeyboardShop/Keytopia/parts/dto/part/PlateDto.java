package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;

@Getter
public class PlateDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private String material;
    private String color;
    private String sizeName;
}
