package KeyboardShop.Keytopia.parts.dto.part;

import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartItemDto {
    private Double price;
    private String name;
    private PartType partType;
    private String imageUrl;

    public PartItemDto(Part part) {
        this.name = part.getName();
        this.price = part.getPrice();
        this.imageUrl = part.getImageUrl();
        this.partType = part.getPartType();
    }

    public PartItemDto(Keyboard keyboard) {
        this.name = keyboard.getName();
        this.price = keyboard.getPrice();
    }
}
