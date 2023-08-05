package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.KeycapSetDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "keycap_set")
public class KeycapSet extends Part {
    @Column(name="keycap_quantity")
    private int keycapQuantity;
    @Column(name="keycap_set_material")
    private KeycapMaterial material;
    @Column(name="keycap_set_language")
    private String language;
    @Column(name="keycap_set_color")
    private String color;
    @ManyToOne
    @JoinColumn(name="keycap_profile_name", nullable=false)
    private KeycapProfile keycapProfile;
    @ManyToMany
    @JoinTable(name="layout_keycap_set",
            joinColumns=@JoinColumn(name="part_name"),
            inverseJoinColumns=@JoinColumn(name="layout_name")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Layout> supportedLayouts;
    @OneToMany(mappedBy = "keycapSet")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public KeycapSet(KeycapSetDto keycapSetDto, Brand brand, KeycapProfile keycapProfile, List<Layout> layouts,String imageUrl,Supplier supplier){
        super(keycapSetDto.getName(), 0, keycapSetDto.getPrice(), PartType.KEYCAP_SET, keycapSetDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand,imageUrl,supplier);
        this.material = keycapSetDto.getMaterial();
        this.language = keycapSetDto.getLanguage();
        this.keycapQuantity = keycapSetDto.getKeycapQuantity();
        this.keycapProfile = keycapProfile;
        this.supportedLayouts = layouts;
        this.color = keycapSetDto.getColor();
    }
}
