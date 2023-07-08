package KeyboardShop.Keytopia.parts.repository.partData;

import KeyboardShop.Keytopia.parts.model.partData.KeycapProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKeycapProfileRepository extends JpaRepository<KeycapProfile, String> {
}
