package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/part")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;
    
    @PostMapping(value ="/cable", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCable(@Valid @RequestParam("json") final CableDto cableDto, @RequestParam("file") MultipartFile file) {
        partService.createCable(cableDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/case", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCable(@Valid @RequestParam("json") final CaseDto caseDto, @RequestParam("file") MultipartFile file) {
        partService.createCase(caseDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/keycap", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@Valid @RequestParam("json")final KeycapDto keycapDto, @RequestParam("file") MultipartFile file) {
        partService.createKeycap(keycapDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/keycap-set", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@Valid @RequestParam("json") final KeycapSetDto keycapSetDto, @RequestParam("file") MultipartFile file) {
        partService.createKeycapSet(keycapSetDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/pcb", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPCB(@Valid @RequestParam("json") final PCBDto pcbDto, @RequestParam("file") MultipartFile file) {
        partService.createPCB(pcbDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/plate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPlate(@Valid @RequestParam("json") final PlateDto plateDto, @RequestParam("file") MultipartFile file) {
        partService.createPlate(plateDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/stabilizer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@Valid @RequestParam("json") final StabilizerDto stabilizerDto, @RequestParam("file") MultipartFile file) {
        partService.createStabilizer(stabilizerDto,file);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/switch-set", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@Valid @RequestParam("json") final SwitchSetDto switchSetDto, @RequestParam("file") MultipartFile file) {
        partService.createSwitchSet(switchSetDto,file);
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
