package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.model.Product;
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
    private String material;
    @Column(name="KeycapSetLanguage")
    private String language;
    @ManyToOne
    @JoinColumn(name="idKeycapProfile", nullable=false)
    private KeycapProfile keycapProfile;
    @ManyToOne
    @JoinColumn(name="idSize", nullable=false)
    private Size maxSize;
    @ManyToMany
    private List<Layout> supportedLayouts;
}
