package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;

@Getter
public class CableDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private String material;
    private String color;
    private String length;
    private CableConnector keyboardConnector;
    private CableConnector computerConnector;
    private boolean isCoiled;
    private boolean isQuickRelease;
}
