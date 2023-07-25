package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.PlateDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
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
@Entity(name = "plate")
public class Plate extends Part {
    @Column(name="plate_material")
    private String material;
    @Column(name="plate_color")
    private String color;
    @ManyToOne
    @JoinColumn(name="size_name", nullable=false)
    private Size size;
    public Plate(PlateDto plateDto, Brand brand, Size size,String imageUrl,Supplier supplier){
        super(plateDto.getName(), 0, plateDto.getPrice(), PartType.PLATE, plateDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl,supplier);
        this.size = size;
        this.material = plateDto.getMaterial();
        this.color = plateDto.getColor();
    }
}
