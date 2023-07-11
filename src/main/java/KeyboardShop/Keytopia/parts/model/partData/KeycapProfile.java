package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.parts.model.parts.Case;
import KeyboardShop.Keytopia.parts.model.parts.Keycap;
import KeyboardShop.Keytopia.parts.model.parts.KeycapSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "KeycapProfile")
public class KeycapProfile {
    @Id
    @Column(name="KeycapProfileName")
    private String name;
    @OneToMany
    private List<KeycapSet> keycapSets;
    @OneToMany
    private List<Keycap> keycaps;
    @OneToMany
    private List<Case> cases;
    
    public KeycapProfile(KeycapProfileDto keycapProfileDto){
        this.name = keycapProfileDto.getName();   
    }
}
