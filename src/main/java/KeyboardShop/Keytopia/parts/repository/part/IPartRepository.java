package KeyboardShop.Keytopia.parts.repository.part;
import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPartRepository extends JpaRepository<Part, String> {
    @Query("SELECT p FROM Part p WHERE " +
            "(:partType IS NULL OR p.partType = :partType)  AND " +
            "(p.quantity >= :minQuantity) AND " +
            "(:name IS NULL OR LOWER(p.name) LIKE %:name%) ")
    Page<Part> findAllParts(PartType partType, String name, int minQuantity, Pageable pageable);
}
