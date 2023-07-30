package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityCantBeDeletedException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.warehouse.dto.SupplierDto;
import KeyboardShop.Keytopia.warehouse.model.Brand;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.repository.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final ISupplierRepository supplierRepository;
    private final BrandService brandService;
    
    public void create(SupplierDto supplierDto){
        Supplier supplier = supplierRepository.findById(supplierDto.getName()).orElse(null);
        if (supplier != null) throw new WarehouseEntityAlreadyExistsException("Supplier with this name already exists.");
        List<Brand> brands = new ArrayList<>();
        supplierDto.getBrands().forEach((brandName) -> {
            Brand brand = brandService.find(brandName);
            if(brand == null) throw new WarehouseEntityNotFoundException("Brand with name" + brandName + "does not exists");
            brands.add(brand);
        });
        supplierRepository.save(new Supplier(supplierDto,brands));
    }
    public void updateBrands(List<String> brandNames,String name){
        Supplier supplier = supplierRepository.findById(name).orElse(null);
        if (supplier == null) throw new WarehouseEntityNotFoundException("Supplier not found!.");
        List<Brand> brands = new ArrayList<>();
        brandNames.forEach((brandName) -> {
            Brand brand = brandService.find(brandName);
            if(brand == null) throw new WarehouseEntityNotFoundException("Brand with name" + brandName + "does not exists");
            brands.add(brand);
        });
        supplier.setBrands(brands);
        supplierRepository.save(supplier);
    }
    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }
    
    public Page<Supplier> findAll(int pageSize, int pageNumber){
        return supplierRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public void delete(String name){
        Supplier supplier = supplierRepository.findById(name).orElse(null);
        if (supplier == null) throw new WarehouseEntityNotFoundException("Supplier does not exists!");
        if(!supplier.getParts().isEmpty()) throw new WarehouseEntityCantBeDeletedException("Supplier cant be deleted because it has parts connected to it!");

        supplierRepository.delete(supplier);
    }
    public Supplier find(String name){
        return supplierRepository.findById(name).orElse(null);
    }
    public void save(Supplier supplier){
        supplierRepository.save(supplier);
    }
}
