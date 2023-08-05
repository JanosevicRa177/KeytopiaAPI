package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.CableDto;
import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
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
@Entity(name = "cable")
public class Cable extends Part {
    @Column(name="cable_material")
    private String material;
    @Column(name="cable_color")
    private String color;
    @Column(name="cable_length")
    private String length;
    @Column(name="cable_connector_keyboard")
    @Enumerated(EnumType.STRING)
    private CableConnector keyboardConnector;
    @Column(name="cable_connector_computer")
    @Enumerated(EnumType.STRING)
    private CableConnector computerConnector;
    @Column(name="is_coiled")
    private boolean isCoiled;
    @Column(name="is_quick_release")
    private boolean isQuickRelease;
    @OneToMany(mappedBy = "cable")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public Cable(CableDto cableDto, Brand brand, String imageUrl,Supplier supplier){
        super(cableDto.getName(), 0, cableDto.getPrice(), PartType.CABLE, cableDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.material = cableDto.getMaterial();
        this.color = cableDto.getColor();
        this.length = cableDto.getLength();
        this.keyboardConnector = cableDto.getKeyboardConnector();
        this.computerConnector = cableDto.getComputerConnector();
        this.isCoiled = cableDto.getIsCoiled();
        this.isQuickRelease = cableDto.getIsQuickRelease();
    }
}
