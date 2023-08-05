package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.PCB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPCBRepository extends JpaRepository<PCB, String> {
    @Query("SELECT p FROM pcb p WHERE " +
            "(:sizeName IS NULL OR p.size.name = :sizeName)  AND " +
            "(:stabilizerType IS NULL OR p.type = :stabilizerType) AND " +
            "(LOWER(p.name) LIKE %:pcbName%) AND " +
            "(:priceWeight IS NULL OR p.priceWeight = :priceWeight) ")
    Page<PCB> findAllPCBs(PriceWeight priceWeight, StabilizerType stabilizerType, String sizeName, String pcbName, Pageable pageable);
}
