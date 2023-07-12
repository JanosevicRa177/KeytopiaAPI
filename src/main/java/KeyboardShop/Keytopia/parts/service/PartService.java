package KeyboardShop.Keytopia.parts.service;

import KeyboardShop.Keytopia.parts.dto.part.KeycapDto;
import KeyboardShop.Keytopia.parts.model.parts.Keycap;
import KeyboardShop.Keytopia.parts.repository.part.*;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataNotFoundException;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final IBrandRepository brandRepository;

    public void createKeycap(KeycapDto keycapDto){
        Keycap keycap = keycapRepository.findByName(keycapDto.getName());
        if (keycap != null) throw new PartAlreadyExistsException("Keycap with this name already exists.");
        Optional<Brand> brandOptional = brandRepository.findById(keycapDto.getBrandId());
        if (brandOptional.isEmpty()) throw new PartDataNotFoundException("Chosen Brand does not exists.");
        keycapRepository.save(new Keycap(keycapDto,brandOptional.get()));
    }
}
