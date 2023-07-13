package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.utils.excentions.partExceptions.partData.PartDataAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.warehouse.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.warehouse.dto.SupplierDto;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.repository.ISupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final ISupplierRepository supplierRepository;
    
    public void create(SupplierDto supplierDto){
        Supplier supplier = supplierRepository.findById(supplierDto.getName()).orElse(null);
        if (supplier != null) throw new PartDataAlreadyExistsException("Supplier with this name already exists.");
        supplierRepository.save(new Supplier(supplierDto));
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
