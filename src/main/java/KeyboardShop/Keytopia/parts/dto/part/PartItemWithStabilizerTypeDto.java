package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartItemWithStabilizerTypeDto extends PartItemDto{
    private StabilizerType stabilizerType;
    
    public PartItemWithStabilizerTypeDto(Stabilizer stabilizer){
        super(stabilizer);
        stabilizerType  = stabilizer.getType();
    }
}
