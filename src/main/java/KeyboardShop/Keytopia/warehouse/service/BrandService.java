package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.parts.model.partData.Layout;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.warehouse.WarehouseEntityCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.warehouse.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.warehouse.dto.BrandDto;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final IBrandRepository brandRepository;
    
    public void create(BrandDto brandDto){
        Optional<Brand> brandOptional = brandRepository.findById(brandDto.getName());
        if (brandOptional.isPresent()) throw new PartDataAlreadyExistsException("Keycap profile with this name already exists.");
        brandRepository.save(new Brand(brandDto));
    }
    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
    public void delete(String name){
        Optional<Brand> brandOptional= brandRepository.findById(name);
        Brand brand = brandOptional.orElse(null);
        if (brand == null) throw new WarehouseEntityNotFoundException("Brand does not exists!");
        if(!brand.getParts().isEmpty()) throw new WarehouseEntityCantBeDeletedException("Brand cant be deleted because it has parts connected to it!");
        brandRepository.delete(brand);
    }
    public Brand find(String name){
        Optional<Brand> brandOptional = brandRepository.findById(name);
        if(brandOptional.isEmpty()) throw new WarehouseEntityNotFoundException("Brand with name" + name + "does not exists");
        return brandOptional.get();
    }
}
