package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "procurement_part")
public class ProcurementPart {
    @Id
    @Column(name="id_procurement_part")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="part_quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="id_procurement", nullable=false)
    private Procurement procurement;
    @ManyToOne
    @JoinColumn(name="part_name", nullable=false)
    private Part part;
}
