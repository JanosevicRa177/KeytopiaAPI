package KeyboardShop.Keytopia.parts.dto.partData;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SwitchDto {
    private String name;
    private SwitchType switchType;
    private PinType pinType;
    private int actuationForce;
    private double actuationPoint;
}
