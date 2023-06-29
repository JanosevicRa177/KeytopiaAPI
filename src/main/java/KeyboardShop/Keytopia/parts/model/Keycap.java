package KeyboardShop.Keytopia.parts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Keycap")
public class Keycap extends Part{
    @Column(name="KeycapMaterial")
    private String material;
}
