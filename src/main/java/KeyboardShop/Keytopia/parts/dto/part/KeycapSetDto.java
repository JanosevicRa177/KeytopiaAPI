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
    private int keycapQuantity;
    private KeycapMaterial material;
    private String language;
    private String keycapProfile;
    private List<String> layouts;
    private MultipartFile image;
    public KeycapSetDto(KeycapSet keycapSet){
        super(keycapSet.getName(),keycapSet.getQuantity(),keycapSet.getPrice(),keycapSet.getPriceWeight(),keycapSet.getBrand().getName(),keycapSet.getImageUrl());
        this.material = keycapSet.getMaterial();
        this.keycapProfile = keycapSet.getKeycapProfile().getName();
        this.language = keycapSet.getLanguage();
        List<String> layoutNames = new ArrayList<>();
        keycapSet.getSupportedLayouts().forEach(layout -> layoutNames.add(layout.getName()));
        this.layouts = layoutNames;
    }
}
