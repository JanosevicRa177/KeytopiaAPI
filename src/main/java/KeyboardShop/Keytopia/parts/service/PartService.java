package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.repository.part.*;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartCantBeDeletedException;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {
    private final ICableRepository cableRepository;
    private final ICaseRepository caseRepository;
    private final IPlateRepository plateRepository;
    private final IKeycapRepository keycapRepository;
    private final IKeycapSetRepository keycapSetRepository;
    private final IPartRepository partRepository;
    private final ISwitchSetRepository switchSetRepository;
    private final IStabilizerRepository stabilizerRepository;
    private final IPCBRepository pcbRepository;
    private final BrandService brandService;
    private final PartDataService partDataService;

    public void createKeycap(KeycapDto keycapDto){
        Keycap keycap  = keycapRepository.findById(keycapDto.getName()).orElse(null);
        if (keycap != null) throw new PartAlreadyExistsException("Keycap with this name already exists.");
        
        Brand brand = brandService.find(keycapDto.getBrandName());
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapDto.getKeycapProfileName());
        
        keycapRepository.save(new Keycap(keycapDto,brand,keycapProfile));
    }
    public void createKeycapSet(KeycapSetDto keycapSetDto) {
        KeycapSet keycapSet = keycapSetRepository.findById(keycapSetDto.getName()).orElse(null);
        if (keycapSet != null) throw new PartAlreadyExistsException("Keycap set with this name already exists.");

        Brand brand = brandService.find(keycapSetDto.getBrandName());
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapSetDto.getKeycapProfileName());
        List<Layout> layouts = new ArrayList<>();
        keycapSetDto.getLayouts().forEach((name)-> layouts.add(partDataService.findLayout(name)));
        
        keycapSetRepository.save(new KeycapSet(keycapSetDto,brand,keycapProfile,layouts));
    }

    public void createCable(CableDto cableDto) {
        Cable cable = cableRepository.findById(cableDto.getName()).orElse(null);
        if (cable != null) throw new PartAlreadyExistsException("Cable with this name already exists.");

        Brand brand = brandService.find(cableDto.getBrandName());

        cableRepository.save(new Cable(cableDto,brand));
    }
    public void createCase(CaseDto caseDto) {
        Case aCase = caseRepository.findById(caseDto.getName()).orElse(null);
        if (aCase != null) throw new PartAlreadyExistsException("Case with this name already exists.");

        Brand brand = brandService.find(caseDto.getBrandName());
        Size size = partDataService.findSize(caseDto.getSizeName());

        caseRepository.save(new Case(caseDto,brand,size));
    }
    public void createPCB(PCBDto pcbDto) {
        PCB pcb = pcbRepository.findById(pcbDto.getName()).orElse(null);
        if (pcb != null) throw new PartAlreadyExistsException("PCB with this name already exists.");

        Brand brand = brandService.find(pcbDto.getBrandName());
        Size size = partDataService.findSize(pcbDto.getSizeName());

        pcbRepository.save(new PCB(pcbDto,brand,size));
    }
    public void createPlate(PlateDto plateDto) {
        Plate plate = plateRepository.findById(plateDto.getName()).orElse(null);
        if (plate != null) throw new PartAlreadyExistsException("Plate with this name already exists.");

        Brand brand = brandService.find(plateDto.getBrandName());
        Size size = partDataService.findSize(plateDto.getSizeName());

        plateRepository.save(new Plate(plateDto,brand,size));
    }
    public void createStabilizer(StabilizerDto stabilizerDto) {
        Stabilizer stabilizer = stabilizerRepository.findById(stabilizerDto.getName()).orElse(null);
        if (stabilizer != null) throw new PartAlreadyExistsException("Stabilizer with this name already exists.");

        Brand brand = brandService.find(stabilizerDto.getBrandName());

        stabilizerRepository.save(new Stabilizer(stabilizerDto,brand));
    }

    public void createSwitchSet(SwitchSetDto switchSetDto) {
        SwitchSet switchSet = switchSetRepository.findById(switchSetDto.getName()).orElse(null);
        if (switchSet != null) throw new PartAlreadyExistsException("Switch set with this name already exists.");

        Brand brand = brandService.find(switchSetDto.getBrandName());
        Switch aSwitch = partDataService.findSwitch(switchSetDto.getSwitchName());
        
        switchSetRepository.save(new SwitchSet(switchSetDto,brand,aSwitch));
    }

    public void deleteCable(String name){
        Cable cable = cableRepository.findById(name).orElse(null);
        if (cable == null) throw new PartAlreadyExistsException("Cable with this name already exists.");
        
        if(!cable.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Cable cant be deleted because it has procurements connected to it!");
        if(!cable.getProducts().isEmpty()) throw new PartCantBeDeletedException("Cable cant be deleted because it has products connected to it!");

        cableRepository.delete(cable);
    }
    public void deleteCase(String name){
        Case aCase = caseRepository.findById(name).orElse(null);
        if (aCase == null) throw new PartAlreadyExistsException("Case with this name already exists.");

        if(!aCase.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Case cant be deleted because it has procurements connected to it!");
        if(!aCase.getProducts().isEmpty()) throw new PartCantBeDeletedException("Case cant be deleted because it has products connected to it!");

        caseRepository.delete(aCase);
    }
    public void deleteKeycap(String name){
        Keycap keycap = keycapRepository.findById(name).orElse(null);
        if (keycap == null) throw new PartAlreadyExistsException("Keycap with this name already exists.");

        if(!keycap.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Keycap cant be deleted because it has procurements connected to it!");
        if(!keycap.getProducts().isEmpty()) throw new PartCantBeDeletedException("Keycap cant be deleted because it has products connected to it!");

        keycapRepository.delete(keycap);
    }
    public void deleteKeycapSet(String name){
        KeycapSet keycapSet = keycapSetRepository.findById(name).orElse(null);
        if (keycapSet == null) throw new PartAlreadyExistsException("Keycap set with this name already exists.");

        if(!keycapSet.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Keycap set cant be deleted because it has procurements connected to it!");
        if(!keycapSet.getProducts().isEmpty()) throw new PartCantBeDeletedException("Keycap set cant be deleted because it has products connected to it!");

        keycapSetRepository.delete(keycapSet);
    }
    public void deletePCB(String name){
        PCB pcb = pcbRepository.findById(name).orElse(null);
        if (pcb == null) throw new PartAlreadyExistsException("PCB with this name already exists.");

        if(!pcb.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("PCB cant be deleted because it has procurements connected to it!");
        if(!pcb.getProducts().isEmpty()) throw new PartCantBeDeletedException("PCB cant be deleted because it has products connected to it!");

        pcbRepository.delete(pcb);
    }
    public void deletePlate(String name){
        Plate plate = plateRepository.findById(name).orElse(null);
        if (plate == null) throw new PartAlreadyExistsException("Plate with this name already exists.");

        if(!plate.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Plate cant be deleted because it has procurements connected to it!");
        if(!plate.getProducts().isEmpty()) throw new PartCantBeDeletedException("Plate cant be deleted because it has products connected to it!");

        plateRepository.delete(plate);
    }
    public void deleteStabilizer(String name){
        Stabilizer stabilizer = stabilizerRepository.findById(name).orElse(null);
        if (stabilizer == null) throw new PartAlreadyExistsException("Stabilizer with this name already exists.");

        if(!stabilizer.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Stabilizer cant be deleted because it has procurements connected to it!");
        if(!stabilizer.getProducts().isEmpty()) throw new PartCantBeDeletedException("Stabilizer cant be deleted because it has products connected to it!");

        stabilizerRepository.delete(stabilizer);
    }
    public void deleteSwitchSet(String name){
        SwitchSet switchSet = switchSetRepository.findById(name).orElse(null);
        if (switchSet == null) throw new PartAlreadyExistsException("Switch set with this name already exists.");

        if(!switchSet.getProcurementParts().isEmpty()) throw new PartCantBeDeletedException("Switch set cant be deleted because it has procurements connected to it!");
        if(!switchSet.getProducts().isEmpty()) throw new PartCantBeDeletedException("Switch set cant be deleted because it has products connected to it!");

        switchSetRepository.delete(switchSet);
    }
    public Page<Part> findAllParts(PartType partType, int pageSize, int pageNumber){
        return partRepository.findAllByPartType(partType, PageRequest.of(pageNumber, pageSize));
    }
    
}