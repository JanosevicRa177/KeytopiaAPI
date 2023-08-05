package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.PCBDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.enums.PCBType;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
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
@Entity(name = "pcb")
public class PCB extends Part{
    @Column(name="pcbtype")
    private PCBType type;
    @Column(name="btconnect")
    private boolean btConnect;
    @ManyToOne
    @JoinColumn(name="size_name", nullable=false)
    private Size size;
    @Column(name="pcbcolor")
    private String color;
    @Column(name="pin_type")
    private PinType pinType;
    @Column(name="stabilizer_type")
    private StabilizerType stabilizerType;
    @OneToMany(mappedBy = "pcb")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public PCB(PCBDto pcbDto, Brand brand, Size size,String imageUrl,Supplier supplier){
        super(pcbDto.getName(), 0, pcbDto.getPrice(), PartType.PCB, pcbDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.type = pcbDto.getType();
        this.btConnect = pcbDto.getBtConnect();
        this.color = pcbDto.getColor();
        this.pinType = pcbDto.getPinType();
        this.stabilizerType = pcbDto.getStabilizerType();
        this.size = size;
    }
}
