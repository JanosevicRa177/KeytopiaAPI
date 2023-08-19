package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PCBType;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.PCB;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PCBDto extends PartDto{
    private PCBType type;
    private Boolean btConnect;
    private String size;
    private String color;
    private PinType pinType;
    private StabilizerType stabilizerType;
    private MultipartFile image;
    public PCBDto(PCB pcb,boolean isAdmin){
        super(pcb,isAdmin);
        this.type = pcb.getType();
        this.btConnect = pcb.isBtConnect();
        this.size = pcb.getSize().getName();
        this.color = pcb.getColor();
        this.pinType = pcb.getPinType();
        this.stabilizerType = pcb.getStabilizerType();
    }
}
