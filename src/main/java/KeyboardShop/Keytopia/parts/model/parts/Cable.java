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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Cable")
public class Cable extends Part {
    @Column(name="CableMaterial")
    private String material;
    @Column(name="CableColor")
    private String color;
    @Column(name="CableLength")
    private String length;
    @Column(name="CableConnectorKeyboard")
    @Enumerated(EnumType.STRING)
    private CableConnector keyboardConnector;
    @Column(name="CableConnectorComputer")
    @Enumerated(EnumType.STRING)
    private CableConnector computerConnector;
    @Column(name="IsCoiled")
    private boolean isCoiled;
    @Column(name="IsQuickRelease")
    private boolean isQuickRelease;
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
