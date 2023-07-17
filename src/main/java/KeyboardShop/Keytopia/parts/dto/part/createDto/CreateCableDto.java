package KeyboardShop.Keytopia.parts.dto.part.createDto;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Cable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CreateCableDto {
    private PriceWeight priceWeight;
    private String name;
    private Double price;
    private String brandName;
    private String material;
    private String color;
    private String length;
    private CableConnector keyboardConnector;
    private CableConnector computerConnector;
    private Boolean isCoiled;
    private Boolean isQuickRelease;
    private MultipartFile image;
}
