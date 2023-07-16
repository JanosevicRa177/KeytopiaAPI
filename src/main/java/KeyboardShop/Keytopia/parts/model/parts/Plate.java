package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.PlateDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Plate")
public class Plate extends Part {
    @Column(name="PlateMaterial")
    private String material;
    @Column(name="PlateColor")
    private String color;
    @ManyToOne
    @JoinColumn(name="SizeName", nullable=false)
    private Size size;
    public Plate(PlateDto plateDto, Brand brand, Size size,String imageUrl){
        super(plateDto.getName(), 0, plateDto.getPrice(), PartType.PLATE, plateDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl);
        this.size = size;
        this.material = plateDto.getMaterial();
        this.color = plateDto.getColor();
    }
}
