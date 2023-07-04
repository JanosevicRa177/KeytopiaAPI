package KeyboardShop.Keytopia.auth.repository;

import KeyboardShop.Keytopia.auth.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository  extends JpaRepository<Admin, Long> {
}
