package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.parts.Plate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPlateRepository extends JpaRepository<Plate,String> {
    @Query("SELECT p FROM plate p WHERE " +
            "(:sizeName IS NULL OR p.size.name LIKE %:sizeName%) AND " +
            "(LOWER(p.name) LIKE %:plateName%) AND " +
            "(:color IS NULL OR LOWER(p.color) LIKE %:color%) AND " +
            "(:priceWeight IS NULL OR p.priceWeight = :priceWeight) ")
    Page<Plate> findAllPlates(String color, PriceWeight priceWeight, String sizeName, String plateName, Pageable pageable);
}
