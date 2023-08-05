package KeyboardShop.Keytopia.parts.repository.part;

import KeyboardShop.Keytopia.parts.model.enums.PinType;
import KeyboardShop.Keytopia.parts.model.enums.PriceWeight;
import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import KeyboardShop.Keytopia.parts.model.parts.SwitchSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISwitchSetRepository extends JpaRepository<SwitchSet, String> {
    @Query("SELECT s FROM switch_set s WHERE " +
            "(:minSwitchQuantity IS NULL OR s.switchQuantity > :minSwitchQuantity) AND " +
            "(:switchType IS NULL OR s.aSwitch.switchType = :switchType) AND " +
            "(LOWER(s.name) LIKE %:switchSetName%) AND " +
            "(:pinType IS NULL OR s.aSwitch.pinType = :pinType) AND " +
            "(:priceWeight IS NULL OR s.priceWeight = :priceWeight) ")
    Page<SwitchSet> findAllSwitchSets(PinType pinType, PriceWeight priceWeight, int minSwitchQuantity, SwitchType switchType, String switchSetName, Pageable pageable);
}
