package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.warehouse.WarehouseEntityCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.warehouse.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.warehouse.dto.BrandDto;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final IBrandRepository brandRepository;
    
    public void create(BrandDto brandDto){
        Brand brand = brandRepository.findById(brandDto.getName()).orElse(null);
        if (brand != null) throw new PartDataAlreadyExistsException("Brand with this name already exists.");
        brandRepository.save(new Brand(brandDto));
    }
    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
    public Page<Brand> findAll(int pageSize, int pageNumber){
        return brandRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public void delete(String name){
        Brand brand = brandRepository.findById(name).orElse(null);
        if (brand == null) throw new WarehouseEntityNotFoundException("Brand does not exists!");
        if(!brand.getParts().isEmpty()) throw new WarehouseEntityCantBeDeletedException("Brand cant be deleted because it has parts connected to it!");
        brandRepository.delete(brand);
    }
    public Brand find(String name){
        Brand brand = brandRepository.findById(name).orElse(null);
        if(brand == null) throw new WarehouseEntityNotFoundException("Brand with name" + name + "does not exists");
        return brand;
    }
}
