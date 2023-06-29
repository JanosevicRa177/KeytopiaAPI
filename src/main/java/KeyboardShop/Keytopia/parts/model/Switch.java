package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.sales.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private PinType pinType;
    @Column(name="ActuationForce")
    private int actuationForce;
    @Column(name="ActuationPoint")
    private double actuationPoint;
    @OneToMany
    private List<SwitchSet> switchSets;
}
