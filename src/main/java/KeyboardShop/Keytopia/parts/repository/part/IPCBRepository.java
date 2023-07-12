package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.parts.PCB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPCBRepository extends JpaRepository<PCB, String> {
}
