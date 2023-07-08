package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.enums.PCBType;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "PCB")
public class PCB extends Part{
    @Column(name="PCBType")
    private PCBType type;
    @Column(name="BTConnect")
    private boolean btConnect;
    @ManyToOne
    @JoinColumn(name="idSize", nullable=false)
    private Size size;
    @Column(name="PCBColor")
    private String color;
    @Column(name="PinType")
    private PinType pinType;
    @Column(name="StabilizerType")
    private StabilizerType stabilizerType;
}
