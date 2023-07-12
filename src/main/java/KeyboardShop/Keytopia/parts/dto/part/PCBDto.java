package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PCBType;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import lombok.Getter;

@Getter
public class PCBDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private PCBType type;
    private boolean btConnect;
    private String sizeName;
    private String color;
    private PinType pinType;
    private StabilizerType stabilizerType;
}
