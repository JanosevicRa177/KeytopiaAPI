package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.KeycapDto;
import KeyboardShop.Keytopia.parts.dto.part.KeycapSetDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "KeycapSet")
public class KeycapSet extends Part {
    @Column(name="KeycapQuantity")
    private int quantity;
    @Column(name="KeycapSetMaterial")
    private KeycapMaterial material;
    @Column(name="KeycapSetLanguage")
    private String language;
    @ManyToOne
    @JoinColumn(name="KeycapProfileName", nullable=false)
    private KeycapProfile keycapProfile;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Layout> supportedLayouts;
    public KeycapSet(KeycapSetDto keycapSetDto, Brand brand, KeycapProfile keycapProfile, List<Layout> layouts,String imageUrl){
        super(keycapSetDto.getName(), 0, keycapSetDto.getPrice(), PartType.KEYCAP_SET, keycapSetDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand,imageUrl);
        this.material = keycapSetDto.getMaterial();
        this.language = keycapSetDto.getLanguage();
        this.quantity = keycapSetDto.getQuantity();
        this.keycapProfile = keycapProfile;
        this.supportedLayouts = layouts;
    }
}
