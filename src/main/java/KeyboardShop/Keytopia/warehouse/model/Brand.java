package KeyboardShop.Keytopia.warehouse.model;

import KeyboardShop.Keytopia.parts.model.Part;
import KeyboardShop.Keytopia.sales.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Brand")
public class Brand {
    @Id
    @Column(name="idBrand")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="BrandName")
    private String name;
    @Column(name="BrandSlogan")
    private String slogan;
    @ManyToMany
    private List<Supplier> suppliers;
    @OneToMany
    private List<Part> parts;
}
