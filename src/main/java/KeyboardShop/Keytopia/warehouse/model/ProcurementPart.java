package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.parts.model.parts.Part;
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
@Entity(name = "ProcurementPart")
public class ProcurementPart {
    @Id
    @Column(name="idProcurementPart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PartQuantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="idProcurement", nullable=false)
    private Procurement procurement;
    @ManyToOne
    @JoinColumn(name="idPart", nullable=false)
    private Part part;
    @OneToMany
    private List<Procurement> procurements;
}
