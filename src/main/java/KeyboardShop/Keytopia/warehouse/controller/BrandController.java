package KeyboardShop.Keytopia.warehouse.controller;

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
    public ResponseEntity<Void> create(@Valid @RequestBody final BrandDto brandDto) {
        brandService.create(brandDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<BrandDto>> findAll() {
        List<Brand> brands = brandService.findAll();
        List<BrandDto> brandDtos = new ArrayList<>();
        brands.forEach((brand)-> brandDtos.add(new BrandDto(brand)));
        return ResponseEntity.ok(brandDtos);
    }
    @GetMapping("/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<BrandDto>> findAll(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Brand> brandPage = brandService.findAll(pageSize, pageNumber);
        List<BrandDto> brandDtos = new ArrayList<>();
        brandPage.getContent().forEach((brand)-> brandDtos.add(new BrandDto(brand)));
        Page<BrandDto> brandDtoPage = new PageImpl<>(brandDtos,brandPage.getPageable(),brandPage.getTotalElements());
        return ResponseEntity.ok(brandDtoPage);
    }
    @DeleteMapping ("/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@Valid @PathVariable String name) {
        brandService.delete(name);
        return ResponseEntity.ok().build();
    }
}
