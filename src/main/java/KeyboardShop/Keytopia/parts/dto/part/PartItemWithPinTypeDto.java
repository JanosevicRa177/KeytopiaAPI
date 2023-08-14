package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartItemWithPinTypeDto extends PartItemDto{
    private PinType pinType;
    public PartItemWithPinTypeDto(SwitchSet switchSet){
        super(switchSet);
        this.pinType = switchSet.getASwitch().getPinType();
    }
}
