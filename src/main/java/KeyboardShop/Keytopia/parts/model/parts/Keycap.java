package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.KeycapDto;
import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "keycap")
public class Keycap extends Part{
    @Column(name="keycap_material")
    private KeycapMaterial material;
    @ManyToOne
    @JoinColumn(name="keycap_profile_name", nullable=false)
    private KeycapProfile keycapProfile;
    public Keycap(KeycapDto keycapDto, Brand brand,KeycapProfile keycapProfile, String imageUrl,Supplier supplier){
        super(keycapDto.getName(), 0, keycapDto.getPrice(), PartType.KEYCAP, keycapDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand,imageUrl,supplier);
        this.material = keycapDto.getMaterial();
        this.keycapProfile = keycapProfile;
    }
}
