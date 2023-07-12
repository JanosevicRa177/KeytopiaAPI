package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;

import javax.persistence.Column;

public class CaseDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private Long brandId;
    private String material;
    private String color;
}
