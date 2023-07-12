package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.KeycapDto;
import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Keycap")
public class Keycap extends Part{
    @Column(name="KeycapMaterial")
    private KeycapMaterial material;
    public Keycap(KeycapDto keycapDto, Brand brand){
        super(keycapDto.getName(),0,keycapDto.getPrice(), PartType.KEYCAP,keycapDto.getPriceWeight(), new ArrayList<>(),new ArrayList<>(),brand);
        this.material = keycapDto.getMaterial();
    }
}
