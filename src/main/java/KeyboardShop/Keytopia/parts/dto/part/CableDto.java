package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Cable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
