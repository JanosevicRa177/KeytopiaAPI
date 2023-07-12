package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.partData.SizeDto;
import KeyboardShop.Keytopia.parts.model.parts.Case;
import KeyboardShop.Keytopia.parts.model.parts.PCB;
import KeyboardShop.Keytopia.parts.model.parts.Plate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Size")
public class Size {
    @Id
    @Column(name = "SizeName")
    private String name;
    @Column(name = "NeededNumberOfKeys")
    private int neededNumberOfKeys;
    @OneToMany
    private List<PCB> pcbs;
    @OneToMany
    private List<Plate> plates;
    @OneToMany
    private List<Case> cases;
    
    public Size(SizeDto sizeDto){
        this.name = sizeDto.getName();
        this.neededNumberOfKeys = sizeDto.getNeededNumberOfKeys();
    }
}
