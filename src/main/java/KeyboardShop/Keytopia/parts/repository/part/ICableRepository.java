package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.Cable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICableRepository extends JpaRepository<Cable, Long> {
}
