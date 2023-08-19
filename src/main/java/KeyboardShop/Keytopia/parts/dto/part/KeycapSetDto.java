package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.parts.KeycapSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class KeycapSetDto extends  PartDto {
    private Integer keycapQuantity;
    private KeycapMaterial material;
    private String language;
    private String keycapProfile;
    private String color;
    private List<String> layouts;
    private MultipartFile image;
    public KeycapSetDto(KeycapSet keycapSet,boolean isAdmin){
        super(keycapSet,isAdmin);
        this.material = keycapSet.getMaterial();
        this.keycapProfile = keycapSet.getKeycapProfile().getName();
        this.language = keycapSet.getLanguage();
        List<String> layoutNames = new ArrayList<>();
        keycapSet.getSupportedLayouts().forEach(layout -> layoutNames.add(layout.getName()));
        this.layouts = layoutNames;
        this.color = keycapSet.getColor();
        this.keycapQuantity = keycapSet.getKeycapQuantity();
    }
}
