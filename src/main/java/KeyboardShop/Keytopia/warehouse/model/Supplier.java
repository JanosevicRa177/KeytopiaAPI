package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.shared.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "idAddress", referencedColumnName = "idAddress")
    private Address address;
}
