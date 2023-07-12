package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartRepository extends JpaRepository<Part, Long> {
}
