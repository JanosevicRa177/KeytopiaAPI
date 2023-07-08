package KeyboardShop.Keytopia.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Procurement")
public class Procurement {
    @Id
    @Column(name="idProcurement")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ProcurementDate")
    private Date date;
    @Column(name="ProcurementDeadline")
    private Date deadline;
    @OneToMany
    private List<ProcurementPart> procurementParts;
    @ManyToOne
    @JoinColumn(name="SupplierName", nullable=false)
    private Supplier supplier;
}
