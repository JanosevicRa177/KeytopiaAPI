package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
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
        if (supplier != null) throw new PartDataAlreadyExistsException("Supplier with this name already exists.");
        List<Brand> brands = new ArrayList<>();
        supplierDto.getBrands().forEach((brandName) -> brands.add(brandService.find(brandName)));
        supplierRepository.save(new Supplier(supplierDto,brands));
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
        supplierRepository.delete(supplier);
    }
}
