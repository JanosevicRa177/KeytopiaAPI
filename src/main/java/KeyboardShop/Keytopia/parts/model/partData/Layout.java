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
@Entity(name = "Layout")
public class Layout {
    @Id
    @Column(name="LayoutName")
    private String name;
    @Column(name="Localization")
    private String localization;
    @ManyToMany
    @JoinTable(name="layout_keycap_set",
            joinColumns=@JoinColumn(name="LayoutName"),
            inverseJoinColumns=@JoinColumn(name="PartName")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<KeycapSet> supportedKeycapSets;
    
    public Layout(LayoutDto layoutDto){
        this.name = layoutDto.getName();
        this.localization = layoutDto.getLocalization();
    }
}
