package KeyboardShop.Keytopia.sales.repository;

import KeyboardShop.Keytopia.sales.model.DeliveryService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDeliveryServiceRepository extends JpaRepository<DeliveryService, String> {
}
