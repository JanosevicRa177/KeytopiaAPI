package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.StabilizersDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Stabilizer")
public class Stabilizer extends Part {
    @Column(name="StabilizerType")
    @Enumerated(EnumType.STRING)
    private StabilizerType type;
    public Stabilizer(StabilizersDto stabilizerDto, Brand brand, String imageUrl, Supplier supplier){
        super(stabilizerDto.getName(), 0, stabilizerDto.getPrice(), PartType.STABILIZER, stabilizerDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.type = stabilizerDto.getType();
    }
}
