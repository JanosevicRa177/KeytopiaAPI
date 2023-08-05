package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.PCB;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartItemPcbDto extends PartDto{
    private PinType pinType;
    private String size;
    private StabilizerType stabilizerType;
    private Boolean btConnect;


    public PartItemPcbDto(PCB pcb){
        super(pcb);
        this.stabilizerType  = pcb.getStabilizerType();
        this.size = pcb.getSize().getName();
        this.pinType = pcb.getPinType();
        this.btConnect = pcb.isBtConnect();
    }
}
