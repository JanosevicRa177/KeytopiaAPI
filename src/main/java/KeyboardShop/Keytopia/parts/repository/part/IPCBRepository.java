package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.PCB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPCBRepository extends JpaRepository<PCB, String> {
    @Query("SELECT p FROM pcb p WHERE " +
            "(:sizeName IS NULL OR p.size.name = :sizeName)  AND " +
            "(p.quantity >= :minQuantity) AND " +
            "(:pcbName IS NULL OR LOWER(p.name) LIKE %:pcbName%) AND " +
            "(:priceWeight IS NULL OR p.priceWeight = :priceWeight) ")
    Page<PCB> findAllPCBs(PriceWeight priceWeight, String sizeName, String pcbName, int minQuantity, Pageable pageable);
}
