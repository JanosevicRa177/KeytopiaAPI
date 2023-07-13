package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.shared.model.Address;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SupplierDto {
    private String name;
    private String phone;
    private Address address;
    
    public SupplierDto(Supplier supplier){
        this.name = supplier.getName();
        this.phone = supplier.getPhone();
        this.address = supplier.getAddress();
    }
}
