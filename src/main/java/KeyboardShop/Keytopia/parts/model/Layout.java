package KeyboardShop.Keytopia.parts.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Layout")
public class Layout {
    @Id
    @Column(name="LayoutName")
    private String name;
    @Column(name="Lozalization")
    private String Localization;
    @ManyToMany
    private List<KeycapSet> supportedKeycapSets;
}
