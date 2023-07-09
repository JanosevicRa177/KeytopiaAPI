package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.dto.LayoutDto;
import KeyboardShop.Keytopia.parts.dto.SizeDto;
import KeyboardShop.Keytopia.parts.dto.SwitchDto;
import KeyboardShop.Keytopia.parts.service.PartDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/part-data")
@RequiredArgsConstructor
public class PartDataController {

    private final PartDataService partDataService;
    
    @PostMapping("/create/keycap-profile")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycapProfile(@Valid @RequestBody final KeycapProfileDto keycapProfileDto) {
        partDataService.createKeycapProfile(keycapProfileDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/create/layout")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createLayout(@Valid @RequestBody final LayoutDto layoutDto) {
        partDataService.createLayout(layoutDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/create/size")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createSize(@Valid @RequestBody final SizeDto sizeDto) {
        partDataService.createSize(sizeDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/create/switch")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createSwitch(@Valid @RequestBody final SwitchDto switchDto) {
        partDataService.createSwitch(switchDto);
        return ResponseEntity.ok().build();
    }
}
