package KeyboardShop.Keytopia.warehouse.controller;

import KeyboardShop.Keytopia.warehouse.dto.SupplierDto;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.service.SupplierService;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {
    
    private final SupplierService supplierService;
    
    @PostMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> create(@Valid @RequestBody final SupplierDto supplierDto) {
        supplierService.create(supplierDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<SupplierDto>> findAll() {
        List<Supplier> suppliers = supplierService.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();
        suppliers.forEach((supplier)-> {
            List<String> brands = new ArrayList<>();
            supplier.getBrands().forEach((brand)-> brands.add(brand.getName()));
            supplierDtos.add(new SupplierDto(supplier,brands));
        });
        return ResponseEntity.ok(supplierDtos);
    }
    @GetMapping("/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<SupplierDto>> findAll(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Supplier> supplierPage = supplierService.findAll(pageSize, pageNumber);
        List<SupplierDto> supplierDtos = new ArrayList<>();
        supplierPage.getContent().forEach((supplier)-> {
            List<String> brands = new ArrayList<>();
            supplier.getBrands().forEach((brand)-> brands.add(brand.getName()));
            supplierDtos.add(new SupplierDto(supplier,brands)); 
        });
        Page<SupplierDto> supplierDtoPage = new PageImpl<>(supplierDtos,supplierPage.getPageable(),supplierPage.getTotalElements());
        return ResponseEntity.ok(supplierDtoPage);
    }
    @DeleteMapping ("/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@Valid @PathVariable String name) {
        supplierService.delete(name);
        return ResponseEntity.ok().build();
    }
}
