package KeyboardShop.Keytopia.auth.repository;

import KeyboardShop.Keytopia.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
