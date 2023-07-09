package KeyboardShop.Keytopia.parts.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class LayoutDto {
    private String name;
    private String localization;
}
