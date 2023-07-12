package KeyboardShop.Keytopia.warehouse.controller;

import KeyboardShop.Keytopia.parts.dto.partData.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.utils.EntityDtoMapper;
import KeyboardShop.Keytopia.warehouse.dto.BrandDto;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.service.BrandService;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createBrand(@Valid @RequestBody final BrandDto brandDto) {
        brandService.create(brandDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<BrandDto>> findAll() {
        List<Brand> brands = brandService.findAll();
        List<BrandDto> dto = new ArrayList<>(EntityDtoMapper.mapAll(brands,BrandDto.class));
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping ("/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@Valid @PathVariable String name) {
        brandService.delete(name);
        return ResponseEntity.ok().build();
    }
}
