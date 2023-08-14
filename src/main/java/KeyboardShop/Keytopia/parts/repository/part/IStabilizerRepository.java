package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.StabilizerType;
import KeyboardShop.Keytopia.parts.model.parts.Stabilizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStabilizerRepository extends JpaRepository<Stabilizer, String> {
    @Query("SELECT s FROM stabilizer s WHERE " +
            "(:stabilizerType IS NULL OR s.type = :stabilizerType) AND " +
            "(LOWER(s.name) LIKE %:stabilizerName%) AND " +
            "(s.quantity >= :minQuantity) AND " +
            "(:priceWeight IS NULL OR s.priceWeight = :priceWeight) ")
    Page<Stabilizer> findAllStabilizers(PriceWeight priceWeight, String stabilizerName, StabilizerType stabilizerType,
                                        int minQuantity, Pageable pageable);
}
