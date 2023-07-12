package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.partData.KeycapProfileDto;
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
    @OneToMany(fetch = FetchType.EAGER)
    private List<KeycapSet> keycapSets;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Keycap> keycaps;
    
    public KeycapProfile(KeycapProfileDto keycapProfileDto){
        this.name = keycapProfileDto.getName();   
    }
}
