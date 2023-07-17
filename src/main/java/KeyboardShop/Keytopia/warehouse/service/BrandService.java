package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.warehouse.dto.BrandDto;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.repository.IBrandRepository;
import KeyboardShop.Keytopia.warehouse.repository.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final IBrandRepository brandRepository;
    private final ISupplierRepository supplierRepository;
    
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
        List<Supplier> suppliers = new ArrayList<>(brand.getSuppliers());
        suppliers.forEach((supplier) -> {
                    supplier.removeBrand(brand);
                    brand.removeSupplier(supplier);
                    supplierRepository.save(supplier);
        });
        brandRepository.delete(brand);
    }
    public Brand find(String name){
        Brand brand = brandRepository.findById(name).orElse(null);
        if(brand == null) throw new WarehouseEntityNotFoundException("Brand with name" + name + "does not exists");
        return brand;
    }
}
