package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;

@Getter
public class KeycapDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private KeycapMaterial material;
    private String keycapProfileName;
}
