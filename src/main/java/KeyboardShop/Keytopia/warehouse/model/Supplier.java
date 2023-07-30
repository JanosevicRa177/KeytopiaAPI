package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.shared.model.Address;
import KeyboardShop.Keytopia.warehouse.dto.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Supplier")
public class Supplier {
    @Id
    @Column(name="supplier_name")
    private String name;
    @Column(name="supplier_phone")
    private String phone;
    @Column(name="supplier_penals")
    private int penals;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address")
    private Address address;
    @ManyToMany
    @JoinTable(name="brand_supplier",
            joinColumns=@JoinColumn(name="supplier_name"),
            inverseJoinColumns=@JoinColumn(name="brand_name")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Brand> brands;
    @OneToMany(mappedBy = "supplier")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Part> parts;
    public Supplier(SupplierDto supplierDto,List<Brand> brands){
        this.name = supplierDto.getName();
        this.phone = supplierDto.getPhone();
        this.address = supplierDto.getAddress();
        this.brands = brands;
        this.penals = 0;
    }
    public void removeBrand(Brand brand){
        this.brands.remove(brand);
    }
    public void penalize(){ this.penals++; }
}
