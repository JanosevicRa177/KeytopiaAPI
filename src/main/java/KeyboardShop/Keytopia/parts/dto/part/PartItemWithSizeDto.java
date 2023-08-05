package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.parts.CaseEntity;
import KeyboardShop.Keytopia.parts.model.parts.Plate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartItemWithSizeDto extends PartItemDto{
    private String size;
    public PartItemWithSizeDto(CaseEntity aCaseEntity){
        super(aCaseEntity);
        this.size = aCaseEntity.getSize().getName();
    }
    public PartItemWithSizeDto(Plate plate){
        super(plate);
        this.size = plate.getSize().getName();
    }
}
