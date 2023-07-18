package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.StabilizerSize;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class StabilizerDto extends PartDto{
    private StabilizerSize size;
    private StabilizerType type;
    private MultipartFile image;
    public StabilizerDto(Stabilizer stabilizer){
        super(stabilizer.getName(),stabilizer.getQuantity(),stabilizer.getPrice(),stabilizer.getPriceWeight(),stabilizer.getBrand().getName(),stabilizer.getImageUrl());
        this.size = stabilizer.getSize();
        this.type = stabilizer.getType();
    }
}
