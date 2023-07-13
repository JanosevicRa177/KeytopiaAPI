package KeyboardShop.Keytopia.parts.dto.partData;

import KeyboardShop.Keytopia.parts.model.partData.Layout;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LayoutDto {
    private String name;
    private String localization;
    public LayoutDto(Layout layout){
        this.name = layout.getName();
        this.localization = layout.getLocalization();
    }
}
