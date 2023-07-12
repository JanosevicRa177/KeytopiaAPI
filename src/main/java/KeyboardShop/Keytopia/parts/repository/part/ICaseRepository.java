package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICaseRepository extends JpaRepository<Case, String> {
}
