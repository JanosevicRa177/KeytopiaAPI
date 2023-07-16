package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
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
    @Column(name="PartName")
    private String name;
    @Column(name="PartQuantity")
    private int quantity;
    @Column(name="PartPrice")
    private double price;
    @Column(name="PartType")
    @Enumerated(EnumType.STRING)
    private PartType partType;
    @Column(name="PartPriceWeight")
    @Enumerated(EnumType.STRING)
    private PriceWeight priceWeight;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProcurementPart> procurementParts;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name="idBrand", nullable=false)
    private Brand brand;
    @Column(name="PartImage")
    private String image;
}
