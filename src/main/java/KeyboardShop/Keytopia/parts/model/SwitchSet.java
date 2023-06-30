package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.sales.model.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
