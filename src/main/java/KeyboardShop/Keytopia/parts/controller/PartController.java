package KeyboardShop.Keytopia.parts.controller;

import KeyboardShop.Keytopia.auth.model.Role;
import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.service.AuthService;
import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.enums.*;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.utils.UtilFunctions;
import KeyboardShop.Keytopia.utils.excentions.UnsupportedPartTypeException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartNotFoundException;
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
    private final UtilFunctions utilFunctions;
    
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
    public ResponseEntity<Page<PartItemWithSizeDto>> findAllCases(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam String name,
                                                                    @RequestParam(required = false) String color, @RequestParam(required = false) String sizeName,
                                                                    @RequestParam(required = false) PriceWeight priceWeight, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<CaseEntity> partPage = partService.findAllCases(color,sizeName, name,priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemWithSizeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithSizeDto(part)));
        Page<PartItemWithSizeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    @GetMapping("cable/page")
    public ResponseEntity<Page<PartItemDto>> findAllCables(@RequestParam int pageSize, @RequestParam int pageNumber,@RequestParam String name,
                                                                    @RequestParam(required = false) PriceWeight priceWeight, @RequestParam(required = false) String color,
                                                                    @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<Cable> partPage = partService.findAllCables(color, name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> cablePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(cablePageDto);
    }
    @GetMapping("plate/page")
    public ResponseEntity<Page<PartItemWithSizeDto>> findAllPlates(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam String name, 
                                                                   @RequestParam(required = false) String color, @RequestParam(required = false) String sizeName,
                                                                   @RequestParam(required = false) PriceWeight priceWeight, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<Plate> partPage = partService.findAllPlates(color,sizeName, name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemWithSizeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithSizeDto(part)));
        Page<PartItemWithSizeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    @GetMapping("switch-set/page")
    public ResponseEntity<Page<PartItemWithPinTypeDto>> findAllSwitchSets(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) PinType pinType,
                                                                   @RequestParam String name,@RequestParam(required = false) SwitchType switchType,
                                                                   @RequestParam(required = false) String sizeName, @RequestParam(required = false) PriceWeight priceWeight,
                                                                   @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<SwitchSet> partPage = partService.findAllSwitchSets(pinType,sizeName, name, switchType, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemWithPinTypeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithPinTypeDto(part)));
        Page<PartItemWithPinTypeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("keycap-set/page")
    public ResponseEntity<Page<PartItemDto>> findAllKeycapSets(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String color,
                                                                  @RequestParam String name,@RequestParam(required = false) String sizeName,
                                                                  @RequestParam(required = false) PriceWeight priceWeight, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<KeycapSet> partPage = partService.findAllKeycapSets(color,sizeName, name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("stabilizers/page")
    public ResponseEntity<Page<PartItemWithStabilizerTypeDto>> findAllStabilizers(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) StabilizerType stabilizerType,
                                                               @RequestParam String name, @RequestParam(required = false) PriceWeight priceWeight, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<Stabilizer> partPage = partService.findAllStabilizers(stabilizerType, name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemWithStabilizerTypeDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemWithStabilizerTypeDto(part)));
        Page<PartItemWithStabilizerTypeDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("pcb/page")
    public ResponseEntity<Page<PartItemPcbDto>> findAllPCBs(@RequestParam int pageSize, @RequestParam int pageNumber,
                                                                @RequestParam String name, @RequestParam(required = false) PriceWeight priceWeight,
                                                                @RequestParam(required = false) String sizeName, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<PCB> partPage = partService.findAllPCBs(sizeName, name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemPcbDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemPcbDto(part)));
        Page<PartItemPcbDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("keycap/page")
    public ResponseEntity<Page<PartItemDto>> findAllKeycaps(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) PriceWeight priceWeight,
                                                         @RequestParam String name, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        int minQuantity = utilFunctions.getMinQuantity(authHeader);
        Page<Keycap> partPage = partService.findAllKeycaps(name, priceWeight, minQuantity, pageSize, pageNumber);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }

    @GetMapping("page")
    public ResponseEntity<Page<PartItemDto>> findAllParts(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) PartType partType,@RequestParam SortDirection sortDirection,
                                                          @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        String value = "price";
        int minQuantity = 1;
        if(authHeader != null){
            User user = authService.getUserFromHeader(authHeader);
            if(user.getRole() == Role.ADMIN){
                minQuantity = 0;
                value = "quantity";
            }
        }
        Page<Part> partPage = partService.findAllParts(partType, name, minQuantity, pageSize, pageNumber, sortDirection,value);
        List<PartItemDto> partDtos = new ArrayList<>();
        partPage.getContent().forEach((part)-> partDtos.add(new PartItemDto(part)));
        Page<PartItemDto> casePageDto = new PageImpl<>(partDtos, partPage.getPageable(),partPage.getTotalElements());
        return ResponseEntity.ok(casePageDto);
    }
    
    @GetMapping("/{partType}/{name}")
    public ResponseEntity<PartDto> findOnePart(@PathVariable PartType partType, @PathVariable String name,
                                               @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        boolean isAdmin = false;
        if(authHeader != null){
            User user = authService.getUserFromHeader(authHeader);
            if(user.getRole() == Role.ADMIN){
                isAdmin = true;
            }
        }
        
        if(partType == PartType.CABLE){
            Cable cable = partService.findOneCable(name);
            if (cable == null) throw new PartNotFoundException("Cable set with name" + name + " not found!");
            return ResponseEntity.ok(new CableDto(cable,isAdmin));   
        } else if(partType == PartType.CASE){
            CaseEntity aCaseEntity = partService.findOneCase(name);
            if (aCaseEntity == null) throw new PartNotFoundException("Case set with name" + name + " not found!");
            return ResponseEntity.ok(new CaseDto(aCaseEntity,isAdmin));
        } else if(partType == PartType.KEYCAP){
            Keycap keycap = partService.findOneKeycap(name);
            if (keycap == null) throw new PartNotFoundException("Keycap set with name" + name + " not found!");
            return ResponseEntity.ok(new KeycapDto(keycap,isAdmin));
        }else if(partType == PartType.KEYCAP_SET){
            KeycapSet keycapSet = partService.findOneKeycapSet(name);
            if (keycapSet == null) throw new PartNotFoundException("Keycap set set with name" + name + " not found!");
            return ResponseEntity.ok(new KeycapSetDto(keycapSet,isAdmin));
        }else if(partType == PartType.PCB){
            PCB pcb = partService.findOnePCB(name);
            if (pcb == null) throw new PartNotFoundException("PCB set with name" + name + " not found!");
            return ResponseEntity.ok(new PCBDto(pcb,isAdmin));
        }else if(partType == PartType.PLATE){
            Plate plate = partService.findOnePlate(name);
            if (plate == null) throw new PartNotFoundException("Plate set with name" + name + " not found!");
            return ResponseEntity.ok(new PlateDto(plate,isAdmin));
        }else if(partType == PartType.STABILIZER){
            Stabilizer stabilizer = partService.findOneStabilizers(name);
            if (stabilizer == null) throw new PartNotFoundException("Stabilizer set with name" + name + " not found!");
            return ResponseEntity.ok(new StabilizersDto(stabilizer,isAdmin));
        }else if(partType == PartType.SWITCH_SET){
            SwitchSet switchSet = partService.findOneSwitchSet(name);
            if (switchSet == null) throw new PartNotFoundException("Switch set with name" + name + " not found!");
            return ResponseEntity.ok(new SwitchSetDto(switchSet,isAdmin));
        }else{
            throw new UnsupportedPartTypeException();
        }
    }
}
