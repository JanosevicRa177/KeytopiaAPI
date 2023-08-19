package KeyboardShop.Keytopia.auth.repository;

import KeyboardShop.Keytopia.auth.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByEmail(String email);
}
