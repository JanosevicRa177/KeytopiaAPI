package KeyboardShop.Keytopia.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "procurement")
public class Procurement {
    @Id
    @Column(name="id_procurement")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="procurement_date")
    private Date date;
    @Column(name="procurement_deadline")
    private Date deadline;
    @OneToMany(mappedBy = "procurement")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProcurementPart> procurementParts;
    @ManyToOne
    @JoinColumn(name="supplier_name", nullable=false)
    private Supplier supplier;
}
