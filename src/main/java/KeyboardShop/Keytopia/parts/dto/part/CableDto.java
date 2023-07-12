package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Cable;
import lombok.Getter;

@Getter
public class CableDto {
    private final PriceWeight priceWeight;
    private final String name;
    private final double price;
    private final String brandName;
    private final String material;
    private final String color;
    private final String length;
    private final CableConnector keyboardConnector;
    private final CableConnector computerConnector;
    private final boolean isCoiled;
    private final boolean isQuickRelease;
    public CableDto(Cable cable) {
        this.priceWeight = cable.getPriceWeight();
        this.name = cable.getName();
        this.brandName = cable.getBrand().getName();
        this.material = cable.getMaterial();
        this.color = cable.getColor();
        this.length = cable.getLength();
        this.price = cable.getPrice();
        this.keyboardConnector = cable.getKeyboardConnector();
        this.computerConnector = cable.getComputerConnector();
        this.isCoiled = cable.isCoiled();
        this.isQuickRelease = cable.isQuickRelease();
    }
}
