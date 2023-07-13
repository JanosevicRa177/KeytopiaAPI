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
    public ResponseEntity<Page<KeycapProfileDto>> findAllKeycapProfiles(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<KeycapProfile> keycapProfilePage = partDataService.findAllKeycapProfiles(pageSize, pageNumber);
        List<KeycapProfileDto> keycapProfileDtos = new ArrayList<>();
        keycapProfilePage.getContent().forEach((keycapProfile)-> keycapProfileDtos.add(new KeycapProfileDto(keycapProfile)));
        Page<KeycapProfileDto> keycapProfilePageDto = new PageImpl<>(keycapProfileDtos, keycapProfilePage.getPageable(),keycapProfilePage.getTotalElements());
        return ResponseEntity.ok(keycapProfilePageDto);
    }
    @GetMapping("/size/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<SizeDto>> findAllSizes(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Size> sizePage = partDataService.findAllSizes(pageSize, pageNumber);
        List<SizeDto> sizeDtos = new ArrayList<>();
        sizePage.getContent().forEach((size)-> sizeDtos.add(new SizeDto(size)));
        Page<SizeDto> sizePageDto = new PageImpl<>(sizeDtos, sizePage.getPageable(),sizePage.getTotalElements());
        return ResponseEntity.ok(sizePageDto);
    }
    @GetMapping("/layout/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<LayoutDto>> findAllLayouts(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Layout> layoutPage = partDataService.findAllLayouts(pageSize, pageNumber);
        List<LayoutDto> layoutDtos = new ArrayList<>();
        layoutPage.getContent().forEach((layout)-> layoutDtos.add(new LayoutDto(layout)));
        Page<LayoutDto> layoutPageDto = new PageImpl<>(layoutDtos,layoutPage.getPageable(),layoutPage.getTotalElements());
        return ResponseEntity.ok(layoutPageDto);
    }
    @GetMapping("/switch/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<SwitchDto>> findAllSwitches(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Switch> switchPage = partDataService.findAllSwitches(pageSize, pageNumber);
        List<SwitchDto> switchDtos = new ArrayList<>();
        switchPage.getContent().forEach((aSwitch)-> switchDtos.add(new SwitchDto(aSwitch)));
        Page<SwitchDto> switchPageDto = new PageImpl<>(switchDtos,switchPage.getPageable(),switchPage.getTotalElements());
        return ResponseEntity.ok(switchPageDto);
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
    public ResponseEntity<List<String>> findAllKeycapProfiles() {
        List<KeycapProfile> keycapProfiles = partDataService.findAllKeycapProfiles();
        List<String> keycapProfileNames = keycapProfiles.stream()
                .map(KeycapProfile::getName).toList();
        return ResponseEntity.ok(keycapProfileNames);
    }
    @GetMapping("/size")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllSizes() {
        List<Size> sizes = partDataService.findAllSizes();
        List<String> sizeNames = sizes.stream()
                .map(Size::getName).toList();
        return ResponseEntity.ok(sizeNames);
    }
    @GetMapping("/layout")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllLayouts() {
        List<Layout> layouts = partDataService.findAllLayouts();
        List<String> layoutNames = layouts.stream()
                .map(Layout::getName).toList();
        return ResponseEntity.ok(layoutNames);
    }
    @GetMapping("/switch")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<List<String>> findAllSwitches() {
        List<Switch> switches = partDataService.findAllSwitches();
        List<String> switchNames = switches.stream()
                .map(Switch::getName).toList();
        return ResponseEntity.ok(switchNames);
    }
}
