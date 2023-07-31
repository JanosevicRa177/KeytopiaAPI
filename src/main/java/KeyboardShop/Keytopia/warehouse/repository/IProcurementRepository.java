package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import KeyboardShop.Keytopia.warehouse.model.Procurement;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcurementRepository extends JpaRepository<Procurement,Long> {
    Page<Procurement> findAllByState(ProcurementState state, Pageable pageable);
}
