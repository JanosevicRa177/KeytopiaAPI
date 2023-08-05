package KeyboardShop.Keytopia.parts.model.parts;
import javax.persistence.*;

@Entity
public class Keyboard {
    @Id
    @Column(name="keyboard_name") 
    private String name;
    @Column(name="keyboard_quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="pcb_name", nullable=false)
    private PCB pcb;
    @ManyToOne
    @JoinColumn(name="plate_name")
    private Plate plate;
    @ManyToOne
    @JoinColumn(name="case_name", nullable=false)
    private CaseEntity aCaseEntity;
    @ManyToOne
    @JoinColumn(name="stabilizer_name", nullable=false)
    private Stabilizer stabilizer;
    @ManyToOne
    @JoinColumn(name="cable_name")
    private Cable cable;
    @ManyToOne
    @JoinColumn(name="keycap_set_name")
    private KeycapSet keycapSet;
    @ManyToOne
    @JoinColumn(name="switch_set_name")
    private SwitchSet switchSet;
    
}
