package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "KeycapProfile")
public class KeycapProfile {
    @Id
    @Column(name="KeycapProfileName")
    private String name;
    
    public KeycapProfile(KeycapProfileDto keycapProfileDto){
        this.name = keycapProfileDto.getName();   
    }
}
