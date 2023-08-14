package KeyboardShop.Keytopia.parts.model.parts;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions.CantMakeKeyboardException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Keyboard {
    @Id
    @Column(name="keyboard_name") 
    private String name;
    @Column(name="keyboard_quantity")
    private int quantity;
    @Column(name="keyboard_price")
    private double price;
    @Column(name="generated_by_admin")
    private boolean generatedByAdmin;
    @Column(name="swithces_lubed")
    private boolean switchesLubed;
    @Column(name="assembled")
    private boolean assembled;
    @Column(name="keyboard_image", length = 2048)
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="pcb_name", nullable=false)
    private PCB pcb;
    @ManyToOne
    @JoinColumn(name="plate_name")
    private Plate plate;
    @ManyToOne
    @JoinColumn(name="case_name", nullable=false)
    private CaseEntity aCase;
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

    @OneToMany(mappedBy = "keyboard")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
    
    public Keyboard(String name,boolean generatedByAdmin, int quantity, CaseEntity aCase, Cable cable, PCB pcb,
                    Plate plate, Stabilizer stabilizer, SwitchSet switchSet, KeycapSet keycapSet){
        this.name = name;
        this.aCase = aCase;
        this.cable = cable;
        this.pcb = pcb;
        this.plate = plate;
        this.stabilizer = stabilizer;
        this.switchSet = switchSet;
        this.keycapSet = keycapSet;
        this.quantity = quantity;
        calculatePrice();
        this.generatedByAdmin = generatedByAdmin;
    }
    private void calculatePrice(){
        double price = 0;
        
        price += pcb.getPrice();
        price += stabilizer.getPrice();
        price += aCase.getPrice();

        if(cable != null)
            price += cable.getPrice();
        if(plate != null)
            price += plate.getPrice();
        if(switchSet != null)
            price += switchSet.getPrice();
        if(keycapSet != null)
            price += keycapSet.getPrice();

        this.setQuantity(this.getQuantity() + quantity);
    }
    public void make(int quantity){
        if((pcb.getQuantity()-quantity) < 0)
            throw new CantMakeKeyboardException("Not enough pcbs for keyboard "+ this.name + "!");
        pcb.setQuantity(pcb.getQuantity()-quantity);
        
        if((stabilizer.getQuantity()-quantity) < 0)
            throw new CantMakeKeyboardException("Not enough Stabilizers for keyboard "+ this.name + "!");
        stabilizer.setQuantity(stabilizer.getQuantity()-quantity);
        
        if((aCase.getQuantity()-quantity) < 0)
            throw new CantMakeKeyboardException("Not enough Cases for keyboard "+ this.name + "!");
        aCase.setQuantity(aCase.getQuantity()-quantity);
        
        if(cable != null){
            if((cable.getQuantity()-quantity) < 0)
                throw new CantMakeKeyboardException("Not enough Cables for keyboard "+ this.name + "!");
            cable.setQuantity(cable.getQuantity()-quantity);
        }
        if(plate != null){
            if((plate.getQuantity()-quantity) < 0)
                throw new CantMakeKeyboardException("Not enough Plates for keyboard "+ this.name + "!");
            plate.setQuantity(plate.getQuantity()-quantity);
        }
        if(switchSet != null){
            if((switchSet.getQuantity()-quantity) < 0)
                throw new CantMakeKeyboardException("Not enough Switch sets for keyboard "+ this.name + "!");
            switchSet.setQuantity(switchSet.getQuantity()-quantity);
        }
        if(keycapSet != null){
            if((keycapSet.getQuantity()-quantity) < 0)
                throw new CantMakeKeyboardException("Not enough Keycap sets for keyboard "+ this.name + "!");
            keycapSet.setQuantity(keycapSet.getQuantity()-quantity);
        }
        
        this.setQuantity(this.getQuantity()+quantity);
    }
}
