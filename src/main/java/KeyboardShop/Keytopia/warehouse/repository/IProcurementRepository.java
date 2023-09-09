package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.warehouse.model.Procurement;
import KeyboardShop.Keytopia.warehouse.model.enums.ProcurementState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IProcurementRepository extends JpaRepository<Procurement, UUID> {

    @Query("SELECT p FROM procurement p WHERE " +
            "(:state IS NULL OR p.state = :state)")
    Page<Procurement> findAllByState(ProcurementState state, Pageable pageable);
}
