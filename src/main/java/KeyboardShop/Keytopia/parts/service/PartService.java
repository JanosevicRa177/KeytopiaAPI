package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.repository.part.*;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataNotFoundException;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.repository.IBrandRepository;
import KeyboardShop.Keytopia.warehouse.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Keycap> keycapOptional = keycapRepository.findById(keycapDto.getName());
        Keycap keycap = keycapOptional.orElse(null);
        if (keycap != null) throw new PartAlreadyExistsException("Keycap with this name already exists.");
        
        Brand brand = brandService.find(keycapDto.getBrandName());
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapDto.getKeycapProfileName());
        
        keycapRepository.save(new Keycap(keycapDto,brand,keycapProfile));
    }
    public void createKeycapSet(KeycapSetDto keycapSetDto) {
        Optional<KeycapSet> keycapSetOptional = keycapSetRepository.findById(keycapSetDto.getName());
        KeycapSet keycapSet = keycapSetOptional.orElse(null);
        if (keycapSet != null) throw new PartAlreadyExistsException("Keycap set with this name already exists.");

        Brand brand = brandService.find(keycapSetDto.getBrandName());
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapSetDto.getKeycapProfileName());
        List<Layout> layouts = new ArrayList<>();
        keycapSetDto.getLayouts().forEach((name)-> layouts.add(partDataService.findLayout(name)));
        
        keycapSetRepository.save(new KeycapSet(keycapSetDto,brand,keycapProfile,layouts));
    }

    public void createCable(CableDto cableDto) {
        Optional<Cable> cableOptional = cableRepository.findById(cableDto.getName());
        Cable cable = cableOptional.orElse(null);
        if (cable != null) throw new PartAlreadyExistsException("Cable with this name already exists.");

        Brand brand = brandService.find(cableDto.getBrandName());

        cableRepository.save(new Cable(cableDto,brand));
    }
    public void createCase(CaseDto caseDto) {
        Optional<Case> caseOptional = caseRepository.findById(caseDto.getName());
        Case aCase = caseOptional.orElse(null);
        if (aCase != null) throw new PartAlreadyExistsException("Case with this name already exists.");

        Brand brand = brandService.find(caseDto.getBrandName());
        Size size = partDataService.findSize(caseDto.getSizeName());

        caseRepository.save(new Case(caseDto,brand,size));
    }
    public void createPCB(PCBDto pcbDto) {
        Optional<PCB> pcbOptional = pcbRepository.findById(pcbDto.getName());
        PCB pcb = pcbOptional.orElse(null);
        if (pcb != null) throw new PartAlreadyExistsException("PCB with this name already exists.");

        Brand brand = brandService.find(pcbDto.getBrandName());
        Size size = partDataService.findSize(pcbDto.getSizeName());

        pcbRepository.save(new PCB(pcbDto,brand,size));
    }
    public void createPlate(PlateDto plateDto) {
        Optional<Plate> plateOptional = plateRepository.findById(plateDto.getName());
        Plate plate = plateOptional.orElse(null);
        if (plate != null) throw new PartAlreadyExistsException("Plate with this name already exists.");

        Brand brand = brandService.find(plateDto.getBrandName());
        Size size = partDataService.findSize(plateDto.getSizeName());

        plateRepository.save(new Plate(plateDto,brand,size));
    }
    public void createStabilizer(StabilizerDto stabilizerDto) {
        Optional<Stabilizer> stabilizerOptional = stabilizerRepository.findById(stabilizerDto.getName());
        Stabilizer stabilizer = stabilizerOptional.orElse(null);
        if (stabilizer != null) throw new PartAlreadyExistsException("Stabilizer with this name already exists.");

        Brand brand = brandService.find(stabilizerDto.getBrandName());

        stabilizerRepository.save(new Stabilizer(stabilizerDto,brand));
    }

    public void createSwitchSet(SwitchSetDto switchSetDto) {
        Optional<SwitchSet> switchSetOptional = switchSetRepository.findById(switchSetDto.getName());
        SwitchSet switchSet = switchSetOptional.orElse(null);
        if (switchSet != null) throw new PartAlreadyExistsException("Switch set with this name already exists.");

        Brand brand = brandService.find(switchSetDto.getBrandName());
        Switch aSwitch = partDataService.findSwitch(switchSetDto.getSwitchName());
        
        switchSetRepository.save(new SwitchSet(switchSetDto,brand,aSwitch));
    }
}