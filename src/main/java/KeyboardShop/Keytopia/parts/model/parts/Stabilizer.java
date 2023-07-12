package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.StabilizerDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerSize;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Stabilizer")
public class Stabilizer extends Part {
    @Column(name="StabilizerSize")
    @Enumerated(EnumType.STRING)
    private StabilizerSize size;
    @Column(name="StabilizerType")
    @Enumerated(EnumType.STRING)
    private StabilizerType type;
    public Stabilizer(StabilizerDto stabilizerDto, Brand brand){
        super(stabilizerDto.getName(), 0, stabilizerDto.getPrice(), PartType.STABILIZER, stabilizerDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand);
        this.type = stabilizerDto.getType();
        this.size = stabilizerDto.getSize();
    }
}
