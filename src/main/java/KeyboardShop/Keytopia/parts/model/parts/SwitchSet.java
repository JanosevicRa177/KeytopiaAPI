package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.SwitchSetDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "switch_set")
public class SwitchSet extends Part {
    @Column(name="switch_quantity")
    private int switchQuantity;
    @ManyToOne
    @JoinColumn(name="switch_name", nullable=false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Switch aSwitch;
    @OneToMany(mappedBy = "switchSet")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Keyboard> keyboards;
    public SwitchSet(SwitchSetDto switchSetDto, Brand brand, Switch aSwitch,String imageUrl,Supplier supplier){
        super(switchSetDto.getName(), 0, switchSetDto.getPrice(), PartType.SWITCH_SET, aSwitch.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl,supplier);
        this.switchQuantity = switchSetDto.getSwitchQuantity();
        this.aSwitch = aSwitch;
    }
}
