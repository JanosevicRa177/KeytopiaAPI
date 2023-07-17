package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.SwitchSetDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "SwitchSet")
public class SwitchSet extends Part {
    @Column(name="SwitchQuantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="SwitchName", nullable=false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Switch aSwitch;
    public SwitchSet(SwitchSetDto switchSetDto, Brand brand, Switch aSwitch,String imageUrl){
        super(switchSetDto.getName(), 0, switchSetDto.getPrice(), PartType.SWITCH_SET, aSwitch.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl);
        this.quantity = switchSetDto.getQuantity();
        this.aSwitch = aSwitch;
    }
}
