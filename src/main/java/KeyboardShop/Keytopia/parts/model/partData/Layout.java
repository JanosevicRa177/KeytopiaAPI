package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.partData.LayoutDto;
import KeyboardShop.Keytopia.parts.model.parts.KeycapSet;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "layout")
public class Layout {
    @Id
    @Column(name="layout_name")
    private String name;
    @Column(name="localization")
    private String localization;
    @ManyToMany(mappedBy = "supportedLayouts")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<KeycapSet> supportedKeycapSets;
    
    public Layout(LayoutDto layoutDto){
        this.name = layoutDto.getName();
        this.localization = layoutDto.getLocalization();
    }
}
