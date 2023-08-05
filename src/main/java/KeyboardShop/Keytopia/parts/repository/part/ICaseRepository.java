package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICaseRepository extends JpaRepository<CaseEntity, String> {
    @Query("SELECT c FROM case_entity c WHERE " +
            "(:sizeName IS NULL OR c.size.name = :sizeName)  AND " +
            "(LOWER(c.name) LIKE %:caseName%) AND " +
            "(:color IS NULL OR LOWER(c.color) LIKE %:color%) AND " +
            "(:priceWeight IS NULL OR c.priceWeight = :priceWeight) ")
    Page<CaseEntity> findAllCases(String color,PriceWeight priceWeight, String sizeName, String caseName, Pageable pageable);
}
