package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.shared.model.Address;
import KeyboardShop.Keytopia.warehouse.dto.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Supplier")
public class Supplier {
    @Id
    @Column(name="SupplierName")
    private String name;
    @Column(name="SupplierPhone")
    private String phone;
    @Column(name="SupplierPenals")
    private int penals;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idAddress", referencedColumnName = "idAddress")
    private Address address;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Brand> brands;
    public Supplier(SupplierDto supplierDto){
        this.name = supplierDto.getName();
        this.phone = supplierDto.getPhone();
        this.address = supplierDto.getAddress();
        this.brands = new ArrayList<>();
        this.penals = 0;
    }
}
