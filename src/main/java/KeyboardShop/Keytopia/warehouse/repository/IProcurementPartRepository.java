package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.warehouse.model.ProcurementPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcurementPartRepository extends JpaRepository<ProcurementPart,Long> {
}
