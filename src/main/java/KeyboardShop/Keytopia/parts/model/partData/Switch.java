package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.partData.SwitchDto;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "switch")
public class Switch {
    @Id
    @Column(name="switch_name")
    private String name;
    @Column(name="switch_type")
    private SwitchType switchType;
    @Column(name="pin_type")
    @Enumerated(EnumType.STRING)
    private PinType pinType;
    @Column(name="actuation_force")
    private int actuationForce;
    @Column(name="actuation_point")
    private double actuationPoint;
    @Column(name="switch_price_weight")
    @Enumerated(EnumType.STRING)
    private PriceWeight priceWeight;
    @OneToMany(mappedBy = "aSwitch")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SwitchSet> switchSets;
    
    public Switch(SwitchDto switchDto){
        this.name = switchDto.getName();
        this.actuationForce = switchDto.getActuationForce();
        this.actuationPoint = switchDto.getActuationPoint();
        this.pinType = switchDto.getPinType();
        this.switchType = switchDto.getSwitchType();
        this.priceWeight = switchDto.getPriceWeight();
    }
}
