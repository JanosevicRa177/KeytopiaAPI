package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.enums.KeycapMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "KeycapSet")
public class KeycapSet extends Part {
    @Column(name="KeycapQuantity")
    private int quantity;
    @Column(name="KeycapSetMaterial")
    private KeycapMaterial material;
    @Column(name="KeycapSetLanguage")
    private String language;
    @ManyToOne
    @JoinColumn(name="idKeycapProfile", nullable=false)
    private KeycapProfile keycapProfile;
    @ManyToMany
    private List<Layout> supportedLayouts;
}
