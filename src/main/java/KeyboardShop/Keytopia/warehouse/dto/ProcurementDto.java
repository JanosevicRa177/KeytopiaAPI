package KeyboardShop.Keytopia.warehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProcurementDto {
    private List<PartItemDto> parts;
}
