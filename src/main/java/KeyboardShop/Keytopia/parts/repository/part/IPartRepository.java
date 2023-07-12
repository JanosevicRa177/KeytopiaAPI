package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PartType;
import KeyboardShop.Keytopia.parts.model.parts.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartRepository extends JpaRepository<Part, String> {
    Page<Part> findAllByPartType(PartType partType, Pageable pageable);
}
