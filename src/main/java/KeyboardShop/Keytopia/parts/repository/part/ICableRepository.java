package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Cable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICableRepository extends JpaRepository<Cable, String> {
    @Query("SELECT c FROM cable c WHERE " +
            "(LOWER(c.name) LIKE %:cableName%) AND " +
            "(:color IS NULL OR LOWER(c.color) LIKE %:color%) AND " +
            "(:priceWeight IS NULL OR c.priceWeight = :priceWeight) ")
    Page<Cable> findAllCables(String color, PriceWeight priceWeight, String cableName, Pageable pageable);
}
