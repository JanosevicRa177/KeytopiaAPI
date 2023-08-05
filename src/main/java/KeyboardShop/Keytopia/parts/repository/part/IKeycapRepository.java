package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Keycap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IKeycapRepository extends JpaRepository<Keycap, String> {
    @Query("SELECT k FROM keycap k WHERE " +
            "(LOWER(k.name) LIKE %:name%) AND " +
            "(:priceWeight IS NULL OR k.priceWeight = :priceWeight) ")
    Page<Keycap> findAllKeycaps(String name, PriceWeight priceWeight, Pageable pageable);

}
