package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlateRepository extends JpaRepository<Plate,String> {
}
