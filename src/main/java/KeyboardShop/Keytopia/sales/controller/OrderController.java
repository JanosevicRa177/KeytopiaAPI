package KeyboardShop.Keytopia.sales.controller;

import KeyboardShop.Keytopia.auth.model.User;
import KeyboardShop.Keytopia.auth.service.AuthService;
import KeyboardShop.Keytopia.sales.Dto.CreateOrderDto;
import KeyboardShop.Keytopia.sales.Dto.OrderDto;
import KeyboardShop.Keytopia.sales.Dto.OrderWithBuyerDto;
import KeyboardShop.Keytopia.sales.model.Order;
import KeyboardShop.Keytopia.sales.service.OrderService;
import KeyboardShop.Keytopia.utils.model.SortDirection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final AuthService authService;

    @GetMapping("admin/page")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<OrderWithBuyerDto>> findAllOrdersForAdmin(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) SortDirection sortDirection,
                                                                         @RequestParam(required = false) String idString) {
        UUID id = null;
        try{
            id = UUID.fromString(idString);   
        }catch(Exception ignored){
            
        }
        Page<Order> orderPage = orderService.GetOrders(sortDirection, id, pageSize, pageNumber);
        List<OrderWithBuyerDto> orderDtos = new ArrayList<>();
        orderPage.getContent().forEach((order)-> orderDtos.add(new OrderWithBuyerDto(order)));
        Page<OrderWithBuyerDto> orderPageDto = new PageImpl<>(orderDtos, orderPage.getPageable(),orderPage.getTotalElements());
        return ResponseEntity.ok(orderPageDto);
    }
    @GetMapping("buyer/page")
    @PreAuthorize("isAuthenticated() and hasAuthority('BUYER')")
    public ResponseEntity<Page<OrderDto>> findAllOrdersForBuyer(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) SortDirection sortDirection,
                                                                         @RequestParam String idString, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        UUID id = null;
        try{
            id = UUID.fromString(idString);
        }catch(Exception ignored){

        }
        User user = authService.getUserFromHeader(authHeader);
        Page<Order> orderPage = orderService.GetBuyerOrders(user.getEmail(),sortDirection, id, pageSize, pageNumber);
        List<OrderDto> orderDtos = new ArrayList<>();
        orderPage.getContent().forEach((order)-> orderDtos.add(new OrderDto(order)));
        Page<OrderDto> orderPageDto = new PageImpl<>(orderDtos, orderPage.getPageable(),orderPage.getTotalElements());
        return ResponseEntity.ok(orderPageDto);
    }
    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('BUYER')")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto orderDto, @RequestHeader(required = false, value = HttpHeaders.AUTHORIZATION) final String authHeader) {
        User user = authService.getUserFromHeader(authHeader);
        orderService.makeOrder(user.getEmail(),orderDto.getParts(),orderDto.getDeliveryServiceName());
        return ResponseEntity.ok().build();
    }
}
