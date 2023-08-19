package KeyboardShop.Keytopia.sales.service;

import KeyboardShop.Keytopia.sales.Dto.DeliveryServiceDto;
import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.repository.IDeliveryServiceRepository;
import KeyboardShop.Keytopia.utils.excentions.salesExceptions.DeliveryServiceAlreadyExistsException;
import KeyboardShop.Keytopia.utils.excentions.salesExceptions.DeliveryServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DeliveryServiceService {
    private final IDeliveryServiceRepository deliveryServiceRepository;

    public void deleteDeliveryService(String name){
        DeliveryService deliveryService = deliveryServiceRepository.findById(name).orElse(null);
        if (deliveryService == null) throw new DeliveryServiceNotFoundException();
        deliveryServiceRepository.delete(deliveryService);
    }
    public void updateDeliveryService(DeliveryServiceDto deliveryServiceDto,String oldName){
        DeliveryService deliveryService = deliveryServiceRepository.findById(deliveryServiceDto.getName()).orElse(null);
        if (deliveryService != null && !deliveryService.getName().equals(oldName)) throw new DeliveryServiceAlreadyExistsException();
        deliveryService = deliveryServiceRepository.findById(oldName).orElse(null);
        if (deliveryService == null) throw new DeliveryServiceNotFoundException();
        deliveryServiceRepository.delete(deliveryService);
        deliveryService.update(deliveryServiceDto);
        deliveryServiceRepository.save(deliveryService);
    }
    public void createDeliveryService(DeliveryServiceDto deliveryServiceDto){
        DeliveryService deliveryService = deliveryServiceRepository.findById(deliveryServiceDto.getName()).orElse(null);
        if (deliveryService != null) throw new DeliveryServiceAlreadyExistsException();
        deliveryServiceRepository.save(new DeliveryService(deliveryServiceDto));
    }
    public Page<DeliveryService> findAllDeliveryServices(int pageSize, int pageNumber){
        return deliveryServiceRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
    public DeliveryService findOne(String name){
        return deliveryServiceRepository.findById(name).orElse(null);
    }
    public List<DeliveryService> findAllDeliveryServices(){
        return deliveryServiceRepository.findAll();
    }
}
