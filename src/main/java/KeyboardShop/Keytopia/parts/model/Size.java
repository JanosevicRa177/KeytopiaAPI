package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.sales.model.Order;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Size")
public class Size {
    @Id
    @Column(name = "idSize")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SizeName")
    private String name;
    @Column(name = "NeededNumberOfKeys")
    private int neededNumberOfKeys;
    @OneToMany
    private List<PCB> pcbs;
    @OneToMany
    private List<Plate> plates;
    @OneToMany
    private List<Case> cases;
}
