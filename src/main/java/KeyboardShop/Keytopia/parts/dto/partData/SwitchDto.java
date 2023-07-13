package KeyboardShop.Keytopia.parts.dto.partData;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SwitchDto {
    private String name;
    private SwitchType switchType;
    private PinType pinType;
    private int actuationForce;
    private double actuationPoint;
    private PriceWeight priceWeight;
    public SwitchDto(Switch aSwitch){
        this.name = aSwitch.getName();
        this.switchType = aSwitch.getSwitchType();
        this.pinType = aSwitch.getPinType();
        this.actuationForce = aSwitch.getActuationForce();
        this.actuationPoint = aSwitch.getActuationPoint();
        this.priceWeight = aSwitch.getPriceWeight();
    }
}
