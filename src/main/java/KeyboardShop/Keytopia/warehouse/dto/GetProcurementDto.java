package KeyboardShop.Keytopia.warehouse.dto;

import KeyboardShop.Keytopia.warehouse.model.Procurement;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetProcurementDto {
    private Long id;
    private LocalDate date;
    private LocalDate deadline;
    private ProcurementState state;
    private List<PartWithQuantityDto> procurementParts;
    private String supplierName;   
    public GetProcurementDto(Procurement procurement){
        this.id = procurement.getId();
        this.date = procurement.getDate();
        this.deadline = procurement.getDeadline();
        this.state = procurement.getState();
        this.procurementParts = new ArrayList<>();
        procurement.getProcurementParts().forEach(part-> this.procurementParts.add(new PartWithQuantityDto(part)));
        this.supplierName = procurement.getSupplier().getName();
    }
}
