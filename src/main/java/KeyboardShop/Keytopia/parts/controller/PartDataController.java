package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.partData.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.dto.partData.LayoutDto;
import KeyboardShop.Keytopia.parts.dto.partData.SizeDto;
import KeyboardShop.Keytopia.parts.dto.partData.SwitchDto;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.service.PartDataService;
import KeyboardShop.Keytopia.utils.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/part-data")
@RequiredArgsConstructor
public class PartDataController {

    private final PartDataService partDataService;
    
    @PostMapping("/keycap-profile")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycapProfile(@Valid @RequestBody final KeycapProfileDto keycapProfileDto) {
        partDataService.createKeycapProfile(keycapProfileDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/layout")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createLayout(@Valid @RequestBody final LayoutDto layoutDto) {
        partDataService.createLayout(layoutDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/size")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createSize(@Valid @RequestBody final SizeDto sizeDto) {
        partDataService.createSize(sizeDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/switch")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createSwitch(@Valid @RequestBody final SwitchDto switchDto) {
        partDataService.createSwitch(switchDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/keycap-profile/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<KeycapProfileDto>> findAllKeycapProfile(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<KeycapProfile> keycapProfilePage = partDataService.findAllKeycapProfile(pageSize, pageNumber);
        Page<KeycapProfileDto> dto = new PageImpl<>(EntityDtoMapper.mapAll(keycapProfilePage.getContent(), KeycapProfileDto.class),
                keycapProfilePage.getPageable(),keycapProfilePage.getTotalElements());
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/size/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<SizeDto>> findAllSize(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Size> sizePage = partDataService.findAllSize(pageSize, pageNumber);
        Page<SizeDto> dto = new PageImpl<>(EntityDtoMapper.mapAll(sizePage.getContent(), SizeDto.class),
                sizePage.getPageable(),sizePage.getTotalElements());
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/layout/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<LayoutDto>> findAllLayout(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Layout> layoutPage = partDataService.findAllLayout(pageSize, pageNumber);
        Page<LayoutDto> dto = new PageImpl<>(EntityDtoMapper.mapAll(layoutPage.getContent(), LayoutDto.class),
                layoutPage.getPageable(),layoutPage.getTotalElements());
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/switch/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<SwitchDto>> findAllSwitch(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Switch> switchPage = partDataService.findAllSwitch(pageSize, pageNumber);
        Page<SwitchDto> dto = new PageImpl<>(EntityDtoMapper.mapAll(switchPage.getContent(), SwitchDto.class),
                switchPage.getPageable(),switchPage.getTotalElements());
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping ("/keycap-profile/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteKeycapProfile(@Valid @PathVariable String name) {
        partDataService.deleteKeycapProfile(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping ("/size/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSize(@Valid @PathVariable String name) {
        partDataService.deleteSize(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping ("/layout/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteLayout(@Valid @PathVariable String name) {
        partDataService.deleteLayout(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping ("/switch/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSwitch(@Valid @PathVariable String name) {
        partDataService.deleteSwitch(name);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/keycap-profile")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllKeycapProfile() {
        List<KeycapProfile> keycapProfiles = partDataService.findAllKeycapProfile();
        List<String> keycapProfileNames = keycapProfiles.stream()
                .map(KeycapProfile::getName).toList();
        return ResponseEntity.ok(keycapProfileNames);
    }
    @GetMapping("/size")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllSize() {
        List<Size> sizes = partDataService.findAllSize();
        List<String> sizeNames = sizes.stream()
                .map(Size::getName).toList();
        return ResponseEntity.ok(sizeNames);
    }
    @GetMapping("/layout")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllLayout() {
        List<Layout> layouts = partDataService.findAllLayout();
        List<String> layoutNames = layouts.stream()
                .map(Layout::getName).toList();
        return ResponseEntity.ok(layoutNames);
    }
    @GetMapping("/switch")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllSwitch() {
        List<Switch> switches = partDataService.findAllSwitch();
        List<String> switchNames = switches.stream()
                .map(Switch::getName).toList();
        return ResponseEntity.ok(switchNames);
    }
}
