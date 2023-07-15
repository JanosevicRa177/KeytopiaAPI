package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandDto {
    private String name;
    private String slogan;
    public BrandDto(Brand brand){
        this.name = brand.getName();
        this.slogan = brand.getSlogan();
    }
}
