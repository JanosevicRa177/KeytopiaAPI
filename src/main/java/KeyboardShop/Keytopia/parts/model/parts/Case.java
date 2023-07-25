package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.CaseDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "case_entity")
public class Case extends Part {
    @Column(name="case_material")
    private String material;
    @Column(name="case_color")
    private String color;
    @ManyToOne
    @JoinColumn(name="size_name", nullable=false)
    private Size size;
    public Case(CaseDto caseDto, Brand brand, Size size,String imageUrl,Supplier supplier){
        super(caseDto.getName(), 0, caseDto.getPrice(), PartType.CASE, caseDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl, supplier);
        this.material = caseDto.getMaterial();
        this.color = caseDto.getColor();
        this.size = size;
    }
}
