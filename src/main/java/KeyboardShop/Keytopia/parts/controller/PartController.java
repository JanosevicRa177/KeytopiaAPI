package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.service.PartService;
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
    @DeleteMapping("/cable/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCable(@Valid @PathVariable String name) {
        partService.deleteCable(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/case/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCase(@Valid @PathVariable String name) {
        partService.deleteCase(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/keycap/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteKeycap(@Valid @PathVariable String name) {
        partService.deleteKeycap(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/keycap-set/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteKeycapSet(@Valid @PathVariable String name) {
        partService.deleteKeycapSet(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/pcb/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletePCB(@Valid @PathVariable String name) {
        partService.deletePCB(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/plate/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletePlate(@Valid @PathVariable String name) {
        partService.deletePlate(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/stabilizer/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteStabilizer(@Valid @PathVariable String name) {
        partService.deleteStabilizer(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/switch-set/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSwitchSet(@Valid @PathVariable String name) {
        partService.deleteSwitchSet(name);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{partType}/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<PartDto>> findAllParts(@Valid @PathVariable PartType partType, @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<Part> partPage = partService.findAllParts(partType, pageSize, pageNumber);
        List<PartDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartDto(part)));
        Page<PartDto> cablePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(cablePageDto);
    }
}
