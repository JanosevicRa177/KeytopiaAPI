package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.ProcurementInvalidException;
import KeyboardShop.Keytopia.warehouse.dto.ProcurementDto;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcurementService {
    
    private final PartService partService;
    private final SupplierService supplierService;
    
    public void create(ProcurementDto procurementDto){
        List<Part> parts = new ArrayList<>();
        procurementDto.getParts().forEach((partItemDto -> parts.add(partService.findOnePart(partItemDto.getName()))));
        List<Supplier> suppliers = supplierService.findAll();
        handleProcurementCreation(parts,suppliers);
    }
    
    private void handleProcurementCreation(List<Part> parts, List<Supplier> suppliers){
        suppliers.sort((supplier1, supplier2) -> 
                (parts.stream().filter(part -> supplier2.getParts().contains(part)).toList().size() +
                parts.stream().filter(part -> supplier2.getBrands().contains(part.getBrand())).toList().size())
                -
                (parts.stream().filter(part -> supplier1.getParts().contains(part)).toList().size() +
                 parts.stream().filter(part -> supplier1.getBrands().contains(part.getBrand())).toList().size()));
        Supplier chosen = suppliers.remove(0);
        if(!parts.isEmpty()){
            if((parts.stream().filter(part -> chosen.getParts().contains(part)).toList().size() +
                parts.stream() .filter(part -> chosen.getBrands().contains(part.getBrand())).toList().size()) == 0){
                {
                    StringBuilder partsNames = new StringBuilder();
                    parts.forEach(part -> partsNames.append(part.getName()).append(" ,"));
                    if (partsNames.length() > 0) {
                        partsNames.delete(partsNames.length() - 2, partsNames.length());
                    }
                    throw new ProcurementInvalidException(partsNames.toString());
                }
            }
            return;
        }
        handleProcurementCreation(parts,suppliers);
    }
}
