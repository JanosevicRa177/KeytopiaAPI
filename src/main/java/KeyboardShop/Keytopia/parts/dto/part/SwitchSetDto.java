package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.dto.partData.SwitchDto;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SwitchSetDto extends PartDto {
    
    private SwitchDto aSwitch;
    private String switchName;
    private Integer switchQuantity;
    private MultipartFile image;
    public SwitchSetDto(SwitchSet switchSet,boolean isAdmin){
        super(switchSet,isAdmin);
        this.aSwitch = new SwitchDto(switchSet.getASwitch());
        this.switchQuantity = switchSet.getSwitchQuantity();
    }
}
