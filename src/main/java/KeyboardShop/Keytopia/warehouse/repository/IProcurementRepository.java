package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.warehouse.model.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcurementRepository extends JpaRepository<Procurement,Long> {
}
