package KeyboardShop.Keytopia.parts.model.partData;

import KeyboardShop.Keytopia.parts.dto.partData.SwitchDto;
import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Switch")
public class Switch {
    @Id
    @Column(name="SwitchName")
    private String name;
    @Column(name="SwitchType")
    private SwitchType switchType;
    @Column(name="PinType")
    @Enumerated(EnumType.STRING)
    private PinType pinType;
    @Column(name="ActuationForce")
    private int actuationForce;
    @Column(name="ActuationPoint")
    private double actuationPoint;
    @OneToMany
    private List<SwitchSet> switchSets;
    
    public Switch(SwitchDto switchDto){
        this.name = switchDto.getName();
        this.actuationForce = switchDto.getActuationForce();
        this.actuationPoint = switchDto.getActuationPoint();
        this.pinType = switchDto.getPinType();
        this.switchType = switchDto.getSwitchType();
    }
}
