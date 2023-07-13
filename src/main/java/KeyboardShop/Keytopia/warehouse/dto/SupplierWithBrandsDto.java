package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.shared.model.Address;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SupplierWithBrandsDto {
    private String name;
    private String phone;
    private Address address;
    private List<String> brands;
    private int penals;
    
    public SupplierWithBrandsDto(Supplier supplier, List<String> brands){
        this.name = supplier.getName();
        this.phone = supplier.getPhone();
        this.penals = supplier.getPenals();
        this.brands = brands;
    }
}
