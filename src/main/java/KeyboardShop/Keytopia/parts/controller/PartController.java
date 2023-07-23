package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.dto.part.CableDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.utils.excentions.UnsupportedPartTypeException;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
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
    
    @PostMapping(value ="/cable", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCable(@ModelAttribute final CableDto cableDto) {
            partService.createCable(cableDto);
            return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/case", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createCase(@ModelAttribute final CaseDto caseDto) {
        partService.createCase(caseDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/keycap", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@ModelAttribute final KeycapDto keycapDto) {
        partService.createKeycap(keycapDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/keycap-set", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createKeycap(@ModelAttribute final KeycapSetDto keycapSetDto) {
        partService.createKeycapSet(keycapSetDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/pcb", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPCB(@ModelAttribute final PCBDto pcbDto) {
        partService.createPCB(pcbDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/plate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createPlate(@ModelAttribute final PlateDto plateDto) {
        partService.createPlate(plateDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/stabilizers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@ModelAttribute final StabilizersDto stabilizersDto) {
        partService.createStabilizers(stabilizersDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value ="/switch-set", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createStabilizer(@ModelAttribute final SwitchSetDto switchSetDto) {
        partService.createSwitchSet(switchSetDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{partType}/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletePart(@PathVariable PartType partType, @Valid @PathVariable String name) {
        if(partType == PartType.CABLE){
            partService.deleteCable(name);
            return ResponseEntity.ok().build();
        } else if(partType == PartType.CASE){
            partService.deleteCase(name);
            return ResponseEntity.ok().build();
        } else if(partType == PartType.KEYCAP){
            partService.deleteKeycap(name);
            return ResponseEntity.ok().build();
        }else if(partType == PartType.KEYCAP_SET){
            partService.deleteKeycapSet(name);
            return ResponseEntity.ok().build();
        }else if(partType == PartType.PCB){
            partService.deletePCB(name);
            return ResponseEntity.ok().build();
        }else if(partType == PartType.PLATE){
            partService.deletePlate(name);
            return ResponseEntity.ok().build();
        }else if(partType == PartType.STABILIZER){
            partService.deleteStabilizers(name);
            return ResponseEntity.ok().build();
        }else if(partType == PartType.SWITCH_SET){
            partService.deleteSwitchSet(name);
            return ResponseEntity.ok().build();
        }else{
            throw new UnsupportedPartTypeException();
        }
    }
    @GetMapping("/page")
    public ResponseEntity<Page<PartDto>> findAllParts(@RequestParam PartType partType, @RequestParam int pageSize, @RequestParam int pageNumber,
                                                      @RequestParam String name,@RequestParam SortDirection sortDirection) {
        Page<Part> partPage = partService.findAllParts(partType, pageSize, pageNumber,name,sortDirection);
        List<PartDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartDto(part)));
        Page<PartDto> cablePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(cablePageDto);
    }
    @GetMapping("/{partType}/{name}")
    public ResponseEntity<PartDto> findOnePart(@PathVariable PartType partType, @PathVariable String name) {
        if(partType == PartType.CABLE){
            Cable cable = partService.findOneCable(name);
            return ResponseEntity.ok(new CableDto(cable));   
        } else if(partType == PartType.CASE){
            Case aCase = partService.findOneCase(name);
            return ResponseEntity.ok(new CaseDto(aCase));
        } else if(partType == PartType.KEYCAP){
            Keycap keycap = partService.findOneKeycap(name);
            return ResponseEntity.ok(new KeycapDto(keycap));
        }else if(partType == PartType.KEYCAP_SET){
            KeycapSet keycapSet = partService.findOneKeycapSet(name);
            return ResponseEntity.ok(new KeycapSetDto(keycapSet));
        }else if(partType == PartType.PCB){
            PCB pcb = partService.findOnePCB(name);
            return ResponseEntity.ok(new PCBDto(pcb));
        }else if(partType == PartType.PLATE){
            Plate plate = partService.findOnePlate(name);
            return ResponseEntity.ok(new PlateDto(plate));
        }else if(partType == PartType.STABILIZER){
            Stabilizer stabilizer = partService.findOneStabilizers(name);
            return ResponseEntity.ok(new StabilizersDto(stabilizer));
        }else if(partType == PartType.SWITCH_SET){
            SwitchSet switchSet = partService.findOneSwitchSet(name);
            return ResponseEntity.ok(new SwitchSetDto(switchSet));
        }else{
            throw new UnsupportedPartTypeException();
        }
    }
}
