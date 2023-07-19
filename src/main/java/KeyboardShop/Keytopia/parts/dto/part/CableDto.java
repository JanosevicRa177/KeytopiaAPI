package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.parts.Cable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CableDto extends PartDto {
    private String material;
    private String color;
    private String length;
    private CableConnector keyboardConnector;
    private CableConnector computerConnector;
    private Boolean isCoiled;
    private Boolean isQuickRelease;
    private MultipartFile image;
    
    public CableDto(Cable cable){
        super(cable);
        this.material = cable.getMaterial();
        this.color = cable.getColor();
        this.length = cable.getLength();
        this.keyboardConnector = cable.getKeyboardConnector();
        this.computerConnector = cable.getComputerConnector();
        this.isCoiled = cable.isCoiled();
        this.isQuickRelease = cable.isQuickRelease();
    }
}
