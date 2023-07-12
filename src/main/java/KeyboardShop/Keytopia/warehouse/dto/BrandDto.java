package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.warehouse.model.Brand;
import lombok.Getter;

@Getter
public class BrandDto {
    private final String name;
    private final String slogan;
    public BrandDto(Brand brand){
        this.name = brand.getName();
        this.slogan = brand.getSlogan();
    }
}
