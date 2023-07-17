package KeyboardShop.Keytopia.warehouse.repository;

import KeyboardShop.Keytopia.warehouse.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISupplierRepository extends JpaRepository<Supplier,String> {
}
