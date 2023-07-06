package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.parts.model.enums.StabilizerSize;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import lombok.*;

import javax.persistence.*;

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
}
