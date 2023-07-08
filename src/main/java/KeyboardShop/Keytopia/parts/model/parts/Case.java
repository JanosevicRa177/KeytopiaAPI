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
@Entity(name = "CaseEntity")
public class Case extends Part{
    @Column(name="CaseMaterial")
    private String material;
    @Column(name="CaseColor")
    private String color;
    @ManyToOne
    @JoinColumn(name="idSize", nullable=false)
    private Size size;
}
