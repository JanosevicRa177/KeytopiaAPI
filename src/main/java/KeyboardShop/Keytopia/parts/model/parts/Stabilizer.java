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
@Entity(name = "stabilizer")
public class Stabilizer extends Part {
    @Column(name="stabilizer_type")
    @Enumerated(EnumType.STRING)
    private StabilizerType type;
    @OneToMany(mappedBy = "stabilizer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public Stabilizer(StabilizersDto stabilizerDto, Brand brand, String imageUrl, Supplier supplier){
        super(stabilizerDto.getName(), 0, stabilizerDto.getPrice(), PartType.STABILIZER, stabilizerDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.type = stabilizerDto.getType();
    }
}
