package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "Part")
public class Part {
    @Id
    @Column(name="idPart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PartQuantity")
    private int quantity;
    @Column(name="NeededPart")
    private boolean isNeeded;
    @Column(name="PartName")
    private String name;
    @OneToMany
    private List<ProcurementPart> procurementParts;
    @OneToMany
    private List<Product> products;
}
