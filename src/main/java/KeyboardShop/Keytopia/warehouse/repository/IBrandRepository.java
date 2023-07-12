package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.warehouse.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository  extends JpaRepository<Brand,String> {
}
