package KeyboardShop.Keytopia.parts.model.parts;

import KeyboardShop.Keytopia.parts.dto.part.CaseDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.warehouse.model.Brand;
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
@Entity(name = "CaseEntity")
public class Case extends Part {
    @Column(name="CaseMaterial")
    private String material;
    @Column(name="CaseColor")
    private String color;
    @ManyToOne
    @JoinColumn(name="SizeName", nullable=false)
    private Size size;
    public Case(CaseDto caseDto, Brand brand, Size size,String imageUrl){
        super(caseDto.getName(), 0, caseDto.getPrice(), PartType.CASE, caseDto.getPriceWeight(), new ArrayList<>(), new ArrayList<>(), brand, imageUrl);
        this.material = caseDto.getMaterial();
        this.color = caseDto.getColor();
        this.size = size;
    }
}
