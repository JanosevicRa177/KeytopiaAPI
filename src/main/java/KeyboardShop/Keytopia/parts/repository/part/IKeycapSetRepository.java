package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.KeycapSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IKeycapSetRepository extends JpaRepository<KeycapSet, String> {
    @Query("SELECT k FROM keycap_set k WHERE " +
            "(k.keycapQuantity > :minKeycapQuantity) AND " +
            "(LOWER(k.name) LIKE %:keycapSetName%) AND " +
            "(:color IS NULL OR LOWER(k.color) LIKE %:color%) AND " +
            "(:priceWeight IS NULL OR k.priceWeight = :priceWeight) ")
    Page<KeycapSet> findAllKeycapSets(String color, PriceWeight priceWeight, int minKeycapQuantity, String keycapSetName, Pageable pageable);

}
