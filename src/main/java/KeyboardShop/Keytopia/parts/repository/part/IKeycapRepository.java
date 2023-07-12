package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.Keycap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKeycapRepository extends JpaRepository<Keycap, String> {
    public Keycap findByName(String name);
}
