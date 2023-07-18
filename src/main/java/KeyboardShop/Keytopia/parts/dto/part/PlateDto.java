package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.parts.Plate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PlateDto extends PartDto {
    private String material;
    private String color;
    private String size;
    private MultipartFile image;
    public PlateDto(Plate plate){
        super(plate.getName(),plate.getQuantity(),plate.getPrice(),plate.getPriceWeight(),plate.getBrand().getName(),plate.getImageUrl());
        this.material = plate.getMaterial();
        this.color = plate.getColor();
        this.size = plate.getSize().getName();
    }
}
