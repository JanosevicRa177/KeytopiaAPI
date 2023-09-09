package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.shared.model.PartItem;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "procurement")
public class Procurement {
    @Id
    @Column(name="id_procurement")
    private UUID id;
    @Column(name="procurement_date")
    private LocalDate date;
    @Column(name="procurement_deadline")
    private LocalDate deadline;
    @Column(name="procurement_state")
    private ProcurementState state;
    @OneToMany(mappedBy = "procurement")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProcurementPart> procurementParts;
    @ManyToOne
    @JoinColumn(name="supplier_name", nullable=false)
    private Supplier supplier;
    
    public Procurement(List<PartItem> partItems, Supplier supplier){
        this.id = UUID.randomUUID();
        this.state = ProcurementState.PENDING;
        this.supplier = supplier;
        this.date = LocalDate.now();
        this.deadline = LocalDate.now().plusDays(14);
        procurementParts = new ArrayList<>();
        partItems.forEach(partItem -> {
            procurementParts.add(new ProcurementPart(partItem.getQuantity(),this, partItem.getPart()));
        });
    }
}
