package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.StabilizersDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "stabilizers")
public class Stabilizers extends Part {
    @Column(name="stabilizers_type")
    @Enumerated(EnumType.STRING)
    private StabilizerType type;
    @OneToMany(mappedBy = "stabilizers")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public Stabilizers(StabilizersDto stabilizerDto, Brand brand, String imageUrl, Supplier supplier){
        super(stabilizerDto.getName(), 0, stabilizerDto.getPrice(), PartType.STABILIZER, stabilizerDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.type = stabilizerDto.getType();
    }
}
