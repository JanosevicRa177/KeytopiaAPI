package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.part.*;
import KeyboardShop.Keytopia.parts.dto.part.CableDto;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.repository.part.*;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.SupplierNorBrandChosenException;
import KeyboardShop.Keytopia.utils.excentions.utilExceptions.FileUploadException;
import KeyboardShop.Keytopia.utils.storage.IStorageService;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.service.BrandService;
import KeyboardShop.Keytopia.warehouse.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final IStorageService storageService;
    private final SupplierService supplierService;

    public void createKeycap(KeycapDto keycapDto){
        Part part  = partRepository.findById(keycapDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");
        
        Brand brand = brandService.find(keycapDto.getBrand());
        Supplier supplier = supplierService.find(keycapDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapDto.getKeycapProfile());
        
        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(keycapDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        keycapRepository.save(new Keycap(keycapDto,brand,keycapProfile,imageUrl,supplier));
    }
    public void createKeycapSet(KeycapSetDto keycapSetDto) {
        Part part  = partRepository.findById(keycapSetDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(keycapSetDto.getBrand());
        Supplier supplier = supplierService.find(keycapSetDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        KeycapProfile keycapProfile = partDataService.findKeycapProfile(keycapSetDto.getKeycapProfile());
        List<Layout> layouts = new ArrayList<>();
        keycapSetDto.getLayouts().forEach((name)-> layouts.add(partDataService.findLayout(name)));

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(keycapSetDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        keycapSetRepository.save(new KeycapSet(keycapSetDto,brand,keycapProfile,layouts,imageUrl,supplier));
    }

    public void createCable(CableDto cableDto) {
        Part part  = partRepository.findById(cableDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(cableDto.getBrand());
        Supplier supplier = supplierService.find(cableDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(cableDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        cableRepository.save(new Cable(cableDto,brand,imageUrl,supplier));
    }
    public void createCase(CaseDto caseDto) {
        Part part  = partRepository.findById(caseDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");
        
        Brand brand = brandService.find(caseDto.getBrand());
        Supplier supplier = supplierService.find(caseDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        Size size = partDataService.findSize(caseDto.getSize());

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(caseDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        caseRepository.save(new Case(caseDto,brand,size,imageUrl,supplier));
    }
    public void createPCB(PCBDto pcbDto) {
        Part part  = partRepository.findById(pcbDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(pcbDto.getBrand());
        Supplier supplier = supplierService.find(pcbDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        Size size = partDataService.findSize(pcbDto.getSize());

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(pcbDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        pcbRepository.save(new PCB(pcbDto,brand,size,imageUrl,supplier));
    }
    public void createPlate(PlateDto plateDto) {
        Part part  = partRepository.findById(plateDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(plateDto.getBrand());
        Supplier supplier = supplierService.find(plateDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        Size size = partDataService.findSize(plateDto.getSize());
        

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(plateDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        plateRepository.save(new Plate(plateDto,brand,size,imageUrl,supplier));
    }
    public void createStabilizers(StabilizersDto stabilizersDto) {
        Part part  = partRepository.findById(stabilizersDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(stabilizersDto.getBrand());
        Supplier supplier = supplierService.find(stabilizersDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(stabilizersDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        stabilizerRepository.save(new Stabilizer(stabilizersDto,brand,imageUrl,supplier));
    }

    public void createSwitchSet(SwitchSetDto switchSetDto) {
        Part part  = partRepository.findById(switchSetDto.getName()).orElse(null);
        if (part != null) throw new PartAlreadyExistsException("Part with this name already exists.");

        Brand brand = brandService.find(switchSetDto.getBrand());
        Supplier supplier = supplierService.find(switchSetDto.getSupplier());
        if(supplier == null && brand == null) throw new SupplierNorBrandChosenException();
        
        Switch aSwitch = partDataService.findSwitch(switchSetDto.getSwitchName());

        String imageUrl;
        try {
            imageUrl = storageService.uploadFile(switchSetDto.getImage());
        } catch (IOException e) {
            throw new FileUploadException();
        }
        
        switchSetRepository.save(new SwitchSet(switchSetDto,brand,aSwitch,imageUrl,supplier));
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
    public void deleteStabilizers(String name){
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

    public Cable findOneCable(String name){
        Cable cable = cableRepository.findById(name).orElse(null);
        if (cable == null) throw new PartNotFoundException("Cable not found!.");
        return cable;
    }
    public Case findOneCase(String name){
        Case aCase = caseRepository.findById(name).orElse(null);
        if (aCase == null) throw new PartNotFoundException("Case not found!.");
        return aCase;
    }
    public Keycap findOneKeycap(String name){
        Keycap keycap = keycapRepository.findById(name).orElse(null);
        if (keycap == null) throw new PartNotFoundException("Keycap not found!.");
        return keycap;
    }
    public KeycapSet findOneKeycapSet(String name){
        KeycapSet keycapSet = keycapSetRepository.findById(name).orElse(null);
        if (keycapSet == null) throw new PartNotFoundException("Keycap set not found!.");
        return keycapSet;
    }
    public PCB findOnePCB(String name){
        PCB pcb = pcbRepository.findById(name).orElse(null);
        if (pcb == null) throw new PartNotFoundException("PCB not found!.");
        return pcb;
    }
    public Plate findOnePlate(String name){
        Plate plate = plateRepository.findById(name).orElse(null);
        if (plate == null) throw new PartNotFoundException("Plate not found!.");
        return plate;
    }
    public Stabilizer findOneStabilizers(String name){
        Stabilizer stabilizer = stabilizerRepository.findById(name).orElse(null);
        if (stabilizer == null) throw new PartNotFoundException("Stabilizer not found!.");
        return stabilizer;
    }
    public SwitchSet findOneSwitchSet(String name){
        SwitchSet switchSet = switchSetRepository.findById(name).orElse(null);
        if (switchSet == null) throw new PartNotFoundException("Stabilizer not found!.");
        return switchSet;
    }
}