package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartItemWithStabilizerTypeDto extends PartItemDto{
    private StabilizerType stabilizerType;
    
    public PartItemWithStabilizerTypeDto(Stabilizer stabilizer){
        super(stabilizer);
        stabilizerType  = stabilizer.getType();
    }
}
