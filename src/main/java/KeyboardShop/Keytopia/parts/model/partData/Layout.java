package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.LayoutDto;
import KeyboardShop.Keytopia.parts.model.parts.KeycapSet;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Layout")
public class Layout {
    @Id
    @Column(name="LayoutName")
    private String name;
    @Column(name="Lozalization")
    private String Localization;
    @ManyToMany
    private List<KeycapSet> supportedKeycapSets;
    
    public Layout(LayoutDto layoutDto){
        this.name = layoutDto.getName();
        this.Localization = layoutDto.getLocalization();
    }
}
