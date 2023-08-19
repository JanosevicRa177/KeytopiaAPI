package KeyboardShop.Keytopia.shared.model;

import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeyboardItem {
    private Integer quantity;
    private Keyboard keyboard;
}
