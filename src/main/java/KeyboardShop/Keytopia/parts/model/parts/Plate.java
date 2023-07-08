package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.model.partData.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Plate")
public class Plate extends Part {
    @Column(name="PlateMaterial")
    private String material;
    @Column(name="PlateColor")
    private String color;
    @ManyToOne
    @JoinColumn(name="idSize", nullable=false)
    private Size size;
}
