package KeyboardShop.Keytopia.warehouse.service;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.ProcurementActionInvalidException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.ProcurementDeadlineNotYetExpiredException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.ProcurementInvalidException;
import KeyboardShop.Keytopia.utils.excentions.warehouseExceptions.WarehouseEntityNotFoundException;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import KeyboardShop.Keytopia.warehouse.dto.ProcurementDto;
import KeyboardShop.Keytopia.warehouse.model.PartItem;
import KeyboardShop.Keytopia.warehouse.model.Procurement;
import KeyboardShop.Keytopia.warehouse.model.Supplier;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import KeyboardShop.Keytopia.warehouse.repository.IProcurementPartRepository;
import KeyboardShop.Keytopia.warehouse.repository.IProcurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcurementService {
    
    private final PartService partService;
    private final SupplierService supplierService;
    private final IProcurementRepository procurementRepository;
    private  final IProcurementPartRepository procurementPartRepository;
    
    public void realize(Long id){
        Procurement procurement = procurementRepository.findById(id).orElse(null);
        if(procurement == null) throw new WarehouseEntityNotFoundException("Procurement not found!");
        if(procurement.getState() != ProcurementState.PENDING) throw new ProcurementActionInvalidException("Procurement is not pending");
        
        procurement.getProcurementParts().forEach((procurementPart -> {
            Part part = procurementPart.getPart();
            part.increaseQuantityBy(procurementPart.getQuantity());
            partService.save(part);
        }));
        procurement.setState(ProcurementState.REALIZED);
        procurementRepository.save(procurement);
    }
    public void penalize(Long id){
        Procurement procurement = procurementRepository.findById(id).orElse(null);
        if(procurement == null) throw new WarehouseEntityNotFoundException("Procurement not found!");
        if(procurement.getDeadline().isAfter(LocalDate.now())) throw new ProcurementDeadlineNotYetExpiredException();
        if(procurement.getState() != ProcurementState.PENDING) throw new ProcurementActionInvalidException("Procurement is not pending");


        Supplier supplier = procurement.getSupplier();
        supplier.penalize();
        supplierService.save(supplier);
        
        procurement.setState(ProcurementState.CANCELED);
        procurementRepository.save(procurement);
    }
    public void delete(Long id){
        Procurement procurement = procurementRepository.findById(id).orElse(null);
        if(procurement == null) throw new WarehouseEntityNotFoundException("Procurement not found!");
        if(procurement.getState() != ProcurementState.PENDING) throw new ProcurementActionInvalidException("Procurement is not pending");


        procurementPartRepository.deleteAll(procurement.getProcurementParts());
        procurementRepository.delete(procurement);
    }
    public Page<Procurement> findAll(int pageSize, int pageNumber,ProcurementState procurementState, SortDirection direction){
        Sort sort = getSortByDate(direction);
        if(procurementState != ProcurementState.NONE)
            return procurementRepository.findAllByState(procurementState,PageRequest.of(pageNumber, pageSize,sort));
        return procurementRepository.findAll(PageRequest.of(pageNumber, pageSize,sort));
    }

    private Sort getSortByDate(SortDirection direction) {
        Sort sort = Sort.unsorted();
        if(direction == SortDirection.ASC)
            sort = Sort.by("date").ascending();
        else if(direction == SortDirection.DESC)
            sort = Sort.by("date").descending();
        return sort;
    }

    public void create(ProcurementDto procurementDto){
        List<PartItem> partItems = new ArrayList<>();
        procurementDto.getParts().forEach(partItemDto -> partItems.add(new PartItem(partItemDto.getQuantity(),partService.findOnePart(partItemDto.getName()))));
        List<Supplier> suppliers = supplierService.findAll();
        handleProcurementCreation(partItems,suppliers);
    }
    
    private void handleProcurementCreation(List<PartItem> partItems, List<Supplier> suppliers){
        if(partItems.isEmpty()) return;
        
        suppliers.sort((supplier1, supplier2) -> 
                getNumberOfMatchingParts(partItems,supplier2) - getNumberOfMatchingParts(partItems,supplier1));
        
        Supplier chosen = suppliers.get(0);
        List<PartItem> matchingPartItems = getMatchingParts(partItems,chosen);
        
        if((!partItems.isEmpty() && matchingPartItems.size() == 0) || suppliers.isEmpty()) {
            StringBuilder partsNames = new StringBuilder();
            partItems.forEach(part -> partsNames.append(part.getPart().getName()).append(" ,"));
            if (partsNames.length() > 0) {
                partsNames.delete(partsNames.length() - 2, partsNames.length());
            }
            throw new ProcurementInvalidException(partsNames.toString());
        }
        suppliers.remove(0);
        
        Procurement procurement = new Procurement(matchingPartItems,chosen);
        procurementRepository.save(procurement);
        procurementPartRepository.saveAll(procurement.getProcurementParts());
        
        partItems.removeAll(matchingPartItems);
        handleProcurementCreation(partItems,suppliers);
    }
    private int getNumberOfMatchingParts(List<PartItem> partsItems, Supplier supplier){
        return partsItems.stream().filter(partItem -> supplier.getParts().contains(partItem.getPart())).toList().size() +
                partsItems.stream() .filter(partItem -> supplier.getBrands().contains(partItem.getPart().getBrand())).toList().size();
    }
    private List<PartItem> getMatchingParts(List<PartItem> partsItems, Supplier supplier) {
        List<PartItem> matchingParts = new ArrayList<>();
        matchingParts.addAll(partsItems.stream().filter(partItem -> supplier.getParts().contains(partItem.getPart())).toList());
        matchingParts.addAll(partsItems.stream().filter(partItem -> supplier.getBrands().contains(partItem.getPart().getBrand())).toList());
        return matchingParts;
    }
}
