package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class CaseDto {
    private PriceWeight priceWeight;
    private String name;
    private double price;
    private String brandName;
    private String material;
    private String color;
    private String sizeName;
}
