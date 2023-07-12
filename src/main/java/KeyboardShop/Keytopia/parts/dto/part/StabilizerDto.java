package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerSize;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import lombok.Getter;

@Getter
public class StabilizerDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private StabilizerSize size;
    private StabilizerType type;
}
