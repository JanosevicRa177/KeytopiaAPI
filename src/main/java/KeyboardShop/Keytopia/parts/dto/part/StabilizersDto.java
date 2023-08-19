package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class StabilizersDto extends PartDto{
    private StabilizerType type;
    private MultipartFile image;
    public StabilizersDto(Stabilizer stabilizer,boolean isAdmin){
        super(stabilizer,isAdmin);
        this.type = stabilizer.getType();
    }
}
