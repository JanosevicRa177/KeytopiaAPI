package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.auth.model.Role;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.security.JwtUtils;
import KeyboardShop.Keytopia.auth.service.AuthService;
import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.dto.part.CableDto;
import KeyboardShop.Keytopia.parts.model.enums.*;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.utils.excentions.UnsupportedPartTypeException;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
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
    private final AuthService authService;
    
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
    @GetMapping("case/page")
    public ResponseEntity<Page<PartItemWithSizeDto>> findAllCases(@RequestParam int pageSize, @RequestParam int pageNumber,
                                                      @RequestParam String name,@RequestParam(required = false) String color,
                                                      @RequestParam(required = false) String sizeName, @RequestParam(required = false) PriceWeight priceWeight) {
        Page<CaseEntity> partPage = partService.findAllCases(color,sizeName, name,priceWeight , pageSize, pageNumber);
        List<PartItemWithSizeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithSizeDto(part)));
        Page<PartItemWithSizeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    @GetMapping("cable/page")
    public ResponseEntity<Page<PartItemDto>> findAllCables(@RequestParam int pageSize, @RequestParam int pageNumber,
                                                      @RequestParam String name,
                                                      @RequestParam(required = false) PriceWeight priceWeight, @RequestParam(required = false) String color) {
        Page<Cable> partPage = partService.findAllCables(color, name, priceWeight, pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> cablePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(cablePageDto);
    }
    @GetMapping("plate/page")
    public ResponseEntity<Page<PartItemWithSizeDto>> findAllPlates(@RequestParam int pageSize, @RequestParam int pageNumber,
                                                                  @RequestParam String name,@RequestParam(required = false) String color,
                                                                  @RequestParam(required = false) String sizeName, @RequestParam(required = false) PriceWeight priceWeight) {
        Page<Plate> partPage = partService.findAllPlates(color,sizeName, name,priceWeight , pageSize, pageNumber);
        List<PartItemWithSizeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithSizeDto(part)));
        Page<PartItemWithSizeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    @GetMapping("switch-set/page")
    public ResponseEntity<Page<PartItemWithPinTypeDto>> findAllSwitchSets(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) PinType pinType,
                                                                   @RequestParam String name,@RequestParam(required = false) SwitchType switchType,
                                                                   @RequestParam(required = false) String sizeName, @RequestParam(required = false) PriceWeight priceWeight) {
        Page<SwitchSet> partPage = partService.findAllSwitchSets(pinType,sizeName, name, switchType, priceWeight , pageSize, pageNumber);
        List<PartItemWithPinTypeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithPinTypeDto(part)));
        Page<PartItemWithPinTypeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("keycap-set/page")
    public ResponseEntity<Page<PartItemDto>> findAllKeycapSets(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String color,
                                                                          @RequestParam String name,@RequestParam(required = false) String sizeName,
                                                                          @RequestParam(required = false) PriceWeight priceWeight) {
        Page<KeycapSet> partPage = partService.findAllKeycapSets(color,sizeName, name, priceWeight , pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("stabilizers/page")
    public ResponseEntity<Page<PartItemWithStabilizerTypeDto>> findAllStabilizers(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) StabilizerType stabilizerType,
                                                               @RequestParam String name, @RequestParam(required = false) PriceWeight priceWeight) {
        Page<Stabilizer> partPage = partService.findAllStabilizers(stabilizerType,name, priceWeight , pageSize, pageNumber);
        List<PartItemWithStabilizerTypeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithStabilizerTypeDto(part)));
        Page<PartItemWithStabilizerTypeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("pcb/page")
    public ResponseEntity<Page<PartItemPcbDto>> findAllPCBs(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) StabilizerType stabilizerType,
                                                                @RequestParam String name, @RequestParam(required = false) PriceWeight priceWeight,
                                                                @RequestParam(required = false) String sizeName) {
        Page<PCB> partPage = partService.findAllPCBs(sizeName, stabilizerType, name, priceWeight , pageSize, pageNumber);
        List<PartItemPcbDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemPcbDto(part)));
        Page<PartItemPcbDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("keycap/page")
    public ResponseEntity<Page<PartItemDto>> findAllKeycaps(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) PriceWeight priceWeight,
                                                         @RequestParam String name) {
        Page<Keycap> partPage = partService.findAllKeycaps(name, priceWeight, pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("page")
    public ResponseEntity<Page<PartItemDto>> findAllParts(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) PartType partType,@RequestParam SortDirection sortDirection,
                                                          @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        User user = authService.getUserFromHeader(authHeader);
        String value = user.getRole() == Role.ADMIN ? "quantity" : "price";
        Page<Part> partPage = partService.findAllParts(partType, name, pageSize, pageNumber, sortDirection,value);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    
    @GetMapping("/{partType}/{name}")
    public ResponseEntity<PartDto> findOnePart(@PathVariable PartType partType, @PathVariable String name) {
        if(partType == PartType.CABLE){
            Cable cable = partService.findOneCable(name);
            return ResponseEntity.ok(new CableDto(cable));   
        } else if(partType == PartType.CASE){
            CaseEntity aCaseEntity = partService.findOneCase(name);
            return ResponseEntity.ok(new CaseDto(aCaseEntity));
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
