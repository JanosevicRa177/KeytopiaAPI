package KeyboardShop.Keytopia.warehouse.controller;

import KeyboardShop.Keytopia.warehouse.dto.ProcurementDto;
import KeyboardShop.Keytopia.warehouse.service.ProcurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
