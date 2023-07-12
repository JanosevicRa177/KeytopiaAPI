package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KeycapSetDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private int quantity;
    private KeycapMaterial material;
    private String language;
    private String keycapProfileName;
    private List<String> layouts;
}
