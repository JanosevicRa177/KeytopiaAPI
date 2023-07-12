package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/part")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;

    @PostMapping("/cable")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCable(@Valid @RequestBody final CableDto cableDto) {
        partService.createCable(cableDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/case")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCable(@Valid @RequestBody final CaseDto caseDto) {
        partService.createCase(caseDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/keycap")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@Valid @RequestBody final KeycapDto keycapDto) {
        partService.createKeycap(keycapDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/keycap-set")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@Valid @RequestBody final KeycapSetDto keycapSetDto) {
        partService.createKeycapSet(keycapSetDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/pcb")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPCB(@Valid @RequestBody final PCBDto pcbDto) {
        partService.createPCB(pcbDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/plate")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPlate(@Valid @RequestBody final PlateDto plateDto) {
        partService.createPlate(plateDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/stabilizer")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@Valid @RequestBody final StabilizerDto stabilizerDto) {
        partService.createStabilizer(stabilizerDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/switch-set")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@Valid @RequestBody final SwitchSetDto switchSetDto) {
        partService.createSwitchSet(switchSetDto);
        return ResponseEntity.ok().build();
    }
}
