package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.PCBDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.enums.PCBType;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

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
    @JoinColumn(name="SizeName", nullable=false)
    private Size size;
    @Column(name="PCBColor")
    private String color;
    @Column(name="PinType")
    private PinType pinType;
    @Column(name="StabilizerType")
    private StabilizerType stabilizerType;
    public PCB(PCBDto pcbDto, Brand brand, Size size,String imageUrl){
        super(pcbDto.getName(), 0, pcbDto.getPrice(), PartType.PCB, pcbDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl);
        this.type = pcbDto.getType();
        this.btConnect = pcbDto.getBtConnect();
        this.color = pcbDto.getColor();
        this.pinType = pcbDto.getPinType();
        this.stabilizerType = pcbDto.getStabilizerType();
        this.size = size;
    }
}
