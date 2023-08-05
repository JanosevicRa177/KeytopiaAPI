package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.parts.CaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CaseDto extends  PartDto{
    private String material;
    private String color;
    private String size;
    private MultipartFile image;
    public CaseDto(CaseEntity aCaseEntity){
        super(aCaseEntity);
        this.material = aCaseEntity.getMaterial();
        this.color = aCaseEntity.getColor();
        this.size = aCaseEntity.getSize().getName();
    }
}
