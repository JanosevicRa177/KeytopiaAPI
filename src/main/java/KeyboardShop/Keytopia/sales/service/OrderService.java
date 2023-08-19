package KeyboardShop.Keytopia.sales.service;

import KeyboardShop.Keytopia.auth.model.Buyer;
import KeyboardShop.Keytopia.auth.repository.IBuyerRepository;
import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.parts.service.KeyboardService;
import KeyboardShop.Keytopia.parts.service.PartService;
import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.model.Order;
import KeyboardShop.Keytopia.sales.model.Product;
import KeyboardShop.Keytopia.sales.repository.IOrderRepository;
import KeyboardShop.Keytopia.utils.excentions.authExceptions.BuyerNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.partExceptions.part.PartNotFoundException;
import KeyboardShop.Keytopia.utils.excentions.salesExceptions.DeliveryServiceNotFoundException;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import KeyboardShop.Keytopia.warehouse.dto.PartWithQuantityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PartService partService;
    private final KeyboardService keyboardService;
    private final IOrderRepository orderRepository;
    private final DeliveryServiceService deliveryServiceService;
    private final IBuyerRepository buyerRepository;

    public void makeOrder(String buyerEmail, List<PartWithQuantityDto> partDtos, String deliveryServiceName){
        Buyer buyer = buyerRepository.findByEmail(buyerEmail);
        if(buyer == null) throw new BuyerNotFoundException();
        DeliveryService deliveryService = deliveryServiceService.findOne(deliveryServiceName);
        if(deliveryService == null) throw new DeliveryServiceNotFoundException();
        Order order = new Order(buyer,deliveryService);
        partDtos.forEach(orderPartDto -> {
            Part part = partService.findOnePartOrNull(orderPartDto.getName());
            Keyboard keyboard;
            if(part != null){
                for(int i = 0; i < orderPartDto.getQuantity();i++)
                    order.addProductToOrder(new Product(part,order));
                partService.decreasePartQuantity(part.getName(),orderPartDto.getQuantity());
                return;
            }
            keyboard = keyboardService.findOneKeyboardOrNull(orderPartDto.getName());
            if(keyboard == null) throw new PartNotFoundException("Couldn't find " + orderPartDto.getName() +"!");
            if(!keyboard.isGeneratedByAdmin())
                keyboardService.makeKeyboard(orderPartDto.getName(),orderPartDto.getQuantity());
            for(int i = 0; i < orderPartDto.getQuantity();i++)
                order.addProductToOrder(new Product(keyboard,order));
            keyboardService.decreaseKeyboardQuantity(keyboard.getName(),orderPartDto.getQuantity());
        } );
        order.calculatePrice();
        orderRepository.save(order);
    };
    
    public Page<Order> GetBuyerOrders(String buyerEmail, SortDirection direction, UUID id, int pageSize, int pageNumber){
        Buyer buyer = buyerRepository.findByEmail(buyerEmail);
        if(buyer == null) throw new BuyerNotFoundException();
        Sort sort = getSortByDate(direction);
        return orderRepository.findAllOrders(buyer, id, PageRequest.of(pageNumber, pageSize,sort));
    };
    public Page<Order> GetOrders(SortDirection direction, UUID id, int pageSize, int pageNumber){
        Sort sort = getSortByDate(direction);
        return orderRepository.findAllOrders(null, id, PageRequest.of(pageNumber, pageSize,sort));
    };
    private Sort getSortByDate(SortDirection direction) {
        Sort sort = Sort.unsorted();
        if(direction == SortDirection.ASC)
            sort = Sort.by("date").ascending();
        else if(direction == SortDirection.DESC)
            sort = Sort.by("date").descending();
        return sort;
    }
}
