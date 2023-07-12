package KeyboardShop.Keytopia.parts.dto.partData;

import KeyboardShop.Keytopia.parts.model.partData.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SizeDto {
    private String name;
    private int neededNumberOfKeys;
    public SizeDto(Size size){
        this.name = size.getName();
        this.neededNumberOfKeys = size.getNeededNumberOfKeys();
    }
}
