package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.warehouse.dto.BrandDto;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Brand")
public class Brand {
    
    @Id
    @Column(name="BrandName")
    private String name;
    @Column(name="BrandSlogan")
    private String slogan;
    @ManyToMany
    private List<Supplier> suppliers;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Part> parts;
    public Brand(BrandDto brandDto){
        this.name = brandDto.getName();
        this.slogan = brandDto.getSlogan();
        this.suppliers = new ArrayList<>();
        this.parts = new ArrayList<>();
    }
}
