package KeyboardShop.Keytopia.parts.repository.partData;

import KeyboardShop.Keytopia.parts.model.partData.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<Size, String> {
}
