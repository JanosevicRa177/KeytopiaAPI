package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.keyboard.CreateKeyboardDto;
import KeyboardShop.Keytopia.parts.model.parts.*;
import KeyboardShop.Keytopia.parts.repository.IKeyboardRepository;
import KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions.KeyboardNameNotPresentException;
import KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions.KeyboardPartNotPresentException;
import KeyboardShop.Keytopia.utils.excentions.KeyboardExceptions.KeyboardWithNameAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions.KeyboardAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions.KeyboardCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.keyboardExceptions.KeyboardNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.utilExceptions.FileUploadException;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import KeyboardShop.Keytopia.utils.storage.IStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeyboardService {
    private final PartService partService;
    private final IKeyboardRepository keyboardRepository;
    private final IStorageService storageService;

    public Keyboard findOneKeyboard(String name){
        Keyboard keyboard = keyboardRepository.findById(name).orElse(null);
        if (keyboard == null) throw new KeyboardNotFoundException("Keyboard with name " + name + " not found!.");
        return keyboard;
    }

    public Page<Keyboard> findAllKeyboards( String name, int minQuantity, Boolean adminGenerated, int pageSize, int pageNumber, SortDirection direction, String value){
        Sort sort = GetSort(direction,value);
        return keyboardRepository.findAllKeyboards(name.toLowerCase(), minQuantity, adminGenerated, PageRequest.of(pageNumber, pageSize,sort));
    }

    public void deleteKeyboard(String name){
        Keyboard keyboard = keyboardRepository.findById(name).orElse(null);
        
        if (keyboard == null) throw new KeyboardNotFoundException("Keyboard not found.");
        if(!keyboard.getProducts().isEmpty()) throw new KeyboardCantBeDeletedException("Keyboard cant be deleted because it has products connected to it!");

        keyboardRepository.delete(keyboard);
    }

    public void commercializeKeyboard(String name,String newName){
        Keyboard keyboard = keyboardRepository.findById(newName).orElse(null);
        if (keyboard != null) throw new KeyboardAlreadyExistsException("Keyboard with this name already exists");
        
        keyboard = keyboardRepository.findById(name).orElse(null);
        if (keyboard == null) throw new KeyboardNotFoundException("Keyboard not found.");

        keyboard.setGeneratedByAdmin(true);
        keyboard.setName(name);
        
        keyboardRepository.save(keyboard);
    }

    public void makeKeyboard(String name,int quantity){
        Keyboard keyboard = keyboardRepository.findById(name).orElse(null);
        if (keyboard == null) throw new KeyboardNotFoundException("Keyboard not found.");

        keyboard.make(quantity);
        keyboardRepository.save(keyboard);
    }

    public void createKeyboard(CreateKeyboardDto keyboardDto,boolean createdByAdmin) {
        
        if(keyboardDto.getCaseEntity() == null) throw new KeyboardPartNotPresentException("Case is not present!");
        CaseEntity aCase  = partService.findOneCase(keyboardDto.getCaseEntity());
        if (aCase == null) throw new PartNotFoundException("Case with name" + keyboardDto.getCaseEntity() + " not found!");

        if(keyboardDto.getPcb() == null) throw new KeyboardPartNotPresentException("PCB is not present!");
        PCB pcb  = partService.findOnePCB(keyboardDto.getPcb());
        if (pcb == null) throw new PartNotFoundException("PCB with name" + keyboardDto.getPcb() + " not found!");

        if(keyboardDto.getStabilizers() == null) throw new KeyboardPartNotPresentException("Stabilizers is not present!");
        Stabilizer stabilizer  = partService.findOneStabilizers(keyboardDto.getStabilizers());
        if (stabilizer == null) throw new PartNotFoundException("Stabilizer with name" + keyboardDto.getStabilizers() + " not found!");

        Cable cable = null;
        if(keyboardDto.getCable() != null) {
            cable  = partService.findOneCable(keyboardDto.getCable());
            if (cable == null && keyboardDto.getCable() != null) throw new PartNotFoundException("Cable with name" + keyboardDto.getCable() + " not found!");
        }
        Plate plate = null;
        if(keyboardDto.getPlate() != null){
            plate  = partService.findOnePlate(keyboardDto.getPlate());
            if (plate == null && keyboardDto.getPlate() != null) throw new PartNotFoundException("Plate with name" + keyboardDto.getPlate() + " not found!");
        }
        SwitchSet switchSet = null;
        if(keyboardDto.getSwitchSet() != null){
            switchSet  = partService.findOneSwitchSet(keyboardDto.getSwitchSet());
            if (switchSet == null && keyboardDto.getSwitchSet() != null) throw new PartNotFoundException("Switch set with name" + keyboardDto.getSwitchSet() + " not found!");
        }
        KeycapSet keycapSet = null;
        if(keyboardDto.getKeycapSet() != null){
            keycapSet  = partService.findOneKeycapSet(keyboardDto.getKeycapSet());
            if (keycapSet == null && keyboardDto.getKeycapSet() != null) throw new PartNotFoundException("Keycap set with name" + keyboardDto.getKeycapSet() + " not found!");
        }
         
        if(createdByAdmin){
            if(keyboardDto.getName() == null || keyboardDto.getName().equals("")) throw new KeyboardNameNotPresentException();
            if(keyboardRepository.findById(keyboardDto.getName()).orElse(null) != null) throw new KeyboardWithNameAlreadyExistsException();
            String imageUrl;
            try {
                imageUrl = storageService.uploadFile(keyboardDto.getImage());
            } catch (IOException e) {
                throw new FileUploadException();
            }
            Keyboard keyboard = new Keyboard(keyboardDto.getName(), true, 0, aCase, cable, pcb, plate, stabilizer, switchSet, keycapSet);
            keyboard.setImageUrl(imageUrl);
            keyboardRepository.save(keyboard);
            return;
        }
        keyboardRepository.save(new Keyboard(UUID.randomUUID().toString(), false,0, aCase, cable, pcb, plate, stabilizer, switchSet, keycapSet));
    }

    private Sort GetSort(SortDirection direction, String value) {
        Sort sort = Sort.unsorted();
        if(direction == SortDirection.ASC)
            sort = Sort.by(value).ascending();
        else if(direction == SortDirection.DESC)
            sort = Sort.by(value).descending();
        return sort;
    }
}
