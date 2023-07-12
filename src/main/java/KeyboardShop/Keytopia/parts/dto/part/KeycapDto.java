package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;

@Getter
public class KeycapDto {
    private PriceWeight priceWeight;
    private KeycapMaterial material;
    private String name;
    private double price;
    private Long brandId;
}
