package KeyboardShop.Keytopia.warehouse.controller;

import KeyboardShop.Keytopia.utils.model.SortDirection;
import KeyboardShop.Keytopia.warehouse.dto.GetProcurementDto;
import KeyboardShop.Keytopia.warehouse.dto.ProcurementDto;
import KeyboardShop.Keytopia.warehouse.model.Procurement;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import KeyboardShop.Keytopia.warehouse.service.ProcurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/procurement")
@RequiredArgsConstructor
public class ProcurementController {
    
    private final ProcurementService procurementService;
    
    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody final ProcurementDto procurementDto) {
        procurementService.create(procurementDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/penalize/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> penalize(@PathVariable Long id) {
        procurementService.penalize(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/realize/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> realize(@PathVariable Long id) {
        procurementService.realize(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        procurementService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("page")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<GetProcurementDto>> findAll(@Valid @RequestParam int pageSize, @Valid @RequestParam int pageNumber,
                                                           @RequestParam(required = false) ProcurementState state, @RequestParam SortDirection sortDirection) {
        Page<Procurement> procurementPage = procurementService.findAll(pageSize, pageNumber,state,sortDirection);
        List<GetProcurementDto> procurementDtos = new ArrayList<>();
        procurementPage.getContent().forEach((procurement)-> {
            procurementDtos.add(new GetProcurementDto(procurement));
        });
        Page<GetProcurementDto> procurementDtoPage = new PageImpl<>(procurementDtos,procurementPage.getPageable(),procurementPage.getTotalElements());
        return ResponseEntity.ok(procurementDtoPage);
    }
    
}
