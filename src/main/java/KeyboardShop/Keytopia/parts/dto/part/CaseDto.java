package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.parts.Case;
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
    public CaseDto(Case aCase){
        super(aCase);
        this.material = aCase.getMaterial();
        this.color = aCase.getColor();
        this.size = aCase.getSize().getName();
    }
}
