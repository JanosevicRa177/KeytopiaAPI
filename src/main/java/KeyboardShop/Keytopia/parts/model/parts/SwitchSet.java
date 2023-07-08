package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.partData.Switch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "SwitchSet")
public class SwitchSet extends Part {
    @Column(name="SwitchQuantity")
    private int quantity;
    @ManyToOne
    private Switch aSwitch;
}
