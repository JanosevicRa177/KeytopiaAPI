package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @Column(name="part_name")
    private String name;
    @Column(name="part_quantity")
    private int quantity;
    @Column(name="part_price")
    private double price;
    @Column(name="part_type")
    @Enumerated(EnumType.STRING)
    private PartType partType;
    @Column(name="part_price_weight")
    @Enumerated(EnumType.STRING)
    private PriceWeight priceWeight;
    @OneToMany(mappedBy = "part")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProcurementPart> procurementParts;
    @OneToMany(mappedBy = "part")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name="brand_name", nullable=true)
    private Brand brand;
    @Column(name="part_image", length = 2048)
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="supplier_name", nullable=true)
    private Supplier supplier;
}
