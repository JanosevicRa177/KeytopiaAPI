package KeyboardShop.Keytopia.sales.repository;

import KeyboardShop.Keytopia.auth.model.Buyer;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.sales.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM order_entity o WHERE " +
            "(:buyer IS NULL OR o.buyer = :buyer) AND " +
            "(:id IS NULL OR o.id = :id) OR " +
            "(:id IS NULL OR EXISTS (SELECT 1 FROM o.products p WHERE p.id = :id))")
    Page<Order> findAllOrders(Buyer buyer, UUID id, Pageable pageable);

}
