package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.partData.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.dto.partData.LayoutDto;
import KeyboardShop.Keytopia.parts.dto.partData.SizeDto;
import KeyboardShop.Keytopia.parts.dto.partData.SwitchDto;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.repository.partData.IKeycapProfileRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ILayoutRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ISizeRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ISwitchRepository;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartDataService {

    private final IKeycapProfileRepository keycapProfileRepository;
    private final ILayoutRepository layoutRepository;
    private final ISizeRepository sizeRepository;
    private final ISwitchRepository switchRepository;
    
    public void deleteKeycapProfile(String name){
        KeycapProfile keycapProfile = keycapProfileRepository.findById(name).orElse(null);
        if (keycapProfile == null) throw new PartDataNotFoundException("KeycapProfile does not exists!");
        if(!keycapProfile.getKeycaps().isEmpty()) throw new PartDataCantBeDeletedException("Keycap profile cant be deleted because it has \"Keycap\" parts connected to it!");
        if(!keycapProfile.getKeycapSets().isEmpty()) throw new PartDataCantBeDeletedException("Keycap profile cant be deleted because it has \"Keycap set\" parts connected to it!");
        keycapProfileRepository.delete(keycapProfile);
    }
    public void deleteSize(String name){
        Size size = sizeRepository.findById(name).orElse(null);
        if (size == null) throw new PartDataNotFoundException("Size does not exists!");
        if(!size.getCaseEntityEntities().isEmpty()) throw new PartDataCantBeDeletedException("Size cant be deleted because it has \"Case\" parts connected to it!");
        if(!size.getPcbs().isEmpty()) throw new PartDataCantBeDeletedException("Size cant be deleted because it has \"Pcb\" parts connected to it!");
        if(!size.getPlates().isEmpty()) throw new PartDataCantBeDeletedException("Size cant be deleted because it has \"Plate\" parts connected to it!");
        sizeRepository.delete(size);
    }
    public void deleteLayout(String name){
        Layout layout = layoutRepository.findById(name).orElse(null);
        if (layout == null) throw new PartDataNotFoundException("Layout does not exists!");
        if(!layout.getSupportedKeycapSets().isEmpty()) throw new PartDataCantBeDeletedException("Layout cant be deleted because it has \"Keycap set\" parts connected to it!");
        layoutRepository.delete(layout);
    }
    public void deleteSwitch(String name){
        Switch aSwitch = switchRepository.findById(name).orElse(null);
        if (aSwitch == null) throw new PartDataNotFoundException("Switch does not exists!");
        if(!aSwitch.getSwitchSets().isEmpty()) throw new PartDataCantBeDeletedException("Switch cant be deleted because it has \"Switch set\" parts connected to it!");
        switchRepository.delete(aSwitch);
    }
    public void createKeycapProfile(KeycapProfileDto keycapProfileDto){
        KeycapProfile keycapProfile = keycapProfileRepository.findById(keycapProfileDto.getName()).orElse(null);
        if (keycapProfile != null) throw new PartDataAlreadyExistsException("Keycap profile with this name already exists.");
        keycapProfileRepository.save(new KeycapProfile(keycapProfileDto));
    }
    public void createSize(SizeDto sizeDto){
        Size size = sizeRepository.findById(sizeDto.getName()).orElse(null);
        if (size != null) throw new PartDataAlreadyExistsException("Size with this name already exists.");
        sizeRepository.save(new Size(sizeDto));
    }
    public void createLayout(LayoutDto layoutDto){
        Layout layout = layoutRepository.findById(layoutDto.getName()).orElse(null);;
        if (layout != null) throw new PartDataAlreadyExistsException("Layout with this name already exists.");
        layoutRepository.save(new Layout(layoutDto));
    }
    public void createSwitch(SwitchDto switchDto){
        Switch aSwitch = switchRepository.findById(switchDto.getName()).orElse(null);
        if (aSwitch != null) throw new PartDataAlreadyExistsException("Switch with this name already exists.");
        switchRepository.save(new Switch(switchDto));
    }
    public Page<KeycapProfile> findAllKeycapProfiles(int pageSize, int pageNumber){
        return keycapProfileRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public Page<Size> findAllSizes(int pageSize, int pageNumber){
        return sizeRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public Page<Layout> findAllLayouts(int pageSize, int pageNumber){
        return layoutRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public Page<Switch> findAllSwitches(int pageSize, int pageNumber){
        return switchRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public List<KeycapProfile> findAllKeycapProfiles(){
        return keycapProfileRepository.findAll();
    }
    public List<Size> findAllSizes(){
        return sizeRepository.findAll();
    }
    public List<Layout> findAllLayouts(){
        return layoutRepository.findAll();
    }
    public List<Switch> findAllSwitches(){
        return switchRepository.findAll();
    }
    public KeycapProfile findKeycapProfile(String name){
        KeycapProfile keycapProfile = keycapProfileRepository.findById(name).orElse(null);
        if(keycapProfile == null) throw new PartDataNotFoundException("Keycap profile with name" + name + "does not exists");
        return keycapProfile;
    }
    public Size findSize(String name){
        Size size = sizeRepository.findById(name).orElse(null);
        if(size == null) throw new PartDataNotFoundException("Size with name" + name + "does not exists");
        return size;
    }
    public Layout findLayout(String name){
        Layout layout = layoutRepository.findById(name).orElse(null);
        if(layout == null) throw new PartDataNotFoundException("Layout with name" + name + "does not exists");
        return layout;
    }
    public Switch findSwitch(String name){
        Switch aSwitch = switchRepository.findById(name).orElse(null);
        if(aSwitch == null) throw new PartDataNotFoundException("Switch with name" + name + "does not exists");
        return aSwitch;
    }
}
