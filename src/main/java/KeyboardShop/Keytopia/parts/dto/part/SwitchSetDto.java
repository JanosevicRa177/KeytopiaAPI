package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SwitchSetDto extends PartDto {
    private String switchName;
    private MultipartFile image;
    public SwitchSetDto(SwitchSet switchSet){
        super(switchSet.getName(),switchSet.getQuantity(),switchSet.getPrice(),switchSet.getPriceWeight(),switchSet.getBrand().getName(),switchSet.getImageUrl());
        this.switchName = switchSet.getASwitch().getName();
    }
}
