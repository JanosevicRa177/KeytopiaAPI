package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.parts.Keycap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class KeycapDto extends PartDto{
    private KeycapMaterial material;
    private String keycapProfile;
    private MultipartFile image;
    public KeycapDto(Keycap keycap){
        super(keycap);
        this.material = keycap.getMaterial();
        this.keycapProfile = keycap.getKeycapProfile().getName();
    }
}
