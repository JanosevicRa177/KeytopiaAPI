package KeyboardShop.Keytopia.sales.controller;

import KeyboardShop.Keytopia.sales.Dto.DeliveryServiceDto;
import KeyboardShop.Keytopia.sales.model.DeliveryService;
import KeyboardShop.Keytopia.sales.service.DeliveryServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/delivery-service")
@RequiredArgsConstructor
public class DeliveryServiceController {
    private final DeliveryServiceService deliveryServiceService;

    @DeleteMapping("/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteDeliveryService(@Valid @PathVariable String name) {
        deliveryServiceService.deleteDeliveryService(name);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{name}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateDeliveryService(@Valid @PathVariable String name,@Valid @RequestBody final DeliveryServiceDto deliveryServiceDto) {
        deliveryServiceService.updateDeliveryService(deliveryServiceDto,name);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Void> createDeliveryService(@Valid @RequestBody final DeliveryServiceDto deliveryServiceDto) {
        deliveryServiceService.createDeliveryService(deliveryServiceDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<String>> findAll() {
        List<DeliveryService> deliveryServices = deliveryServiceService.findAllDeliveryServices();
        List<String> keycapProfileNames = deliveryServices.stream()
                .map(DeliveryService::getName).toList();
        return ResponseEntity.ok(keycapProfileNames);
    }
    @GetMapping("/{pageSize}/{pageNumber}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public ResponseEntity<Page<DeliveryServiceDto>> findAllSwitches(@Valid @PathVariable int pageSize, @Valid @PathVariable int pageNumber) {
        Page<DeliveryService> deliveryServicePage = deliveryServiceService.findAllDeliveryServices(pageSize, pageNumber);
        List<DeliveryServiceDto> deliveryServiceDtos = new ArrayList<>();
        deliveryServicePage.getContent().forEach((deliveryService)-> deliveryServiceDtos.add(new DeliveryServiceDto(deliveryService)));
        Page<DeliveryServiceDto> deliveryServicePageDto = new PageImpl<>(deliveryServiceDtos,deliveryServicePage.getPageable(),deliveryServicePage.getTotalElements());
        return ResponseEntity.ok(deliveryServicePageDto);
    }
}
