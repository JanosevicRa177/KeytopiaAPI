package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.KeycapProfileDto;
import KeyboardShop.Keytopia.parts.dto.LayoutDto;
import KeyboardShop.Keytopia.parts.dto.SizeDto;
import KeyboardShop.Keytopia.parts.dto.SwitchDto;
import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.parts.model.partData.Size;
import KeyboardShop.Keytopia.parts.model.partData.Switch;
import KeyboardShop.Keytopia.parts.repository.partData.IKeycapProfileRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ILayoutRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ISizeRepository;
import KeyboardShop.Keytopia.parts.repository.partData.ISwitchRepository;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.PartDataAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartDataService {
    
    IKeycapProfileRepository keycapProfileRepository;
    ILayoutRepository layoutRepository;
    ISizeRepository sizeRepository;
    ISwitchRepository switchRepository;
    
    public void createKeycapProfile(KeycapProfileDto keycapProfileDto){
        Optional<KeycapProfile> keycapProfile = keycapProfileRepository.findById(keycapProfileDto.getName());
        if (keycapProfile.isPresent()) throw new PartDataAlreadyExistsException("Keycap profile with this name already exists.");
        keycapProfileRepository.save(new KeycapProfile(keycapProfileDto));
    }
    public void createSize(SizeDto sizeDto){
        Optional<Size> size = sizeRepository.findById(sizeDto.getName());
        if (size.isPresent()) throw new PartDataAlreadyExistsException("Size with this name already exists.");
        sizeRepository.save(new Size(sizeDto));
    }
    public void createLayout(LayoutDto layoutDto){
        Optional<Layout> layout = layoutRepository.findById(layoutDto.getName());
        if (layout.isPresent()) throw new PartDataAlreadyExistsException("Layout with this name already exists.");
        layoutRepository.save(new Layout(layoutDto));
    }
    public void createSwitch(SwitchDto switchDto){
        Optional<Switch> aSwitch = switchRepository.findById(switchDto.getName());
        if (aSwitch.isPresent()) throw new PartDataAlreadyExistsException("Switch with this name already exists.");
        switchRepository.save(new Switch(switchDto));
    }
}
