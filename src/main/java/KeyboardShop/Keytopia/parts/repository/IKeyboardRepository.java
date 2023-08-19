package KeyboardShop.Keytopia.parts.repository;

import KeyboardShop.Keytopia.parts.model.parts.Keyboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IKeyboardRepository extends JpaRepository<Keyboard, String> {

    @Query("SELECT k FROM Keyboard k WHERE " +
            "(k.quantity >= :minQuantity) AND " +
            "(:adminGenerated IS NULL OR k.generatedByAdmin = :adminGenerated) AND " +
            "(:name IS NULL OR LOWER(k.name) LIKE %:name%) ")
    Page<Keyboard> findAllKeyboards(String name, int minQuantity, Boolean adminGenerated, Pageable pageable);
    
    @Query("SELECT k FROM Keyboard k WHERE " +
            "(k.generatedByAdmin = false) AND " +
            "k.products IS EMPTY")
    List<Keyboard> findAllUnusedKeyboards();

}
