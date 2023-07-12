package KeyboardShop.Keytopia.parts.dto.partData;

import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeycapProfileDto {
    private String name;
    public KeycapProfileDto(KeycapProfile keycapProfile){
        this.name = keycapProfile.getName();
    }
}
