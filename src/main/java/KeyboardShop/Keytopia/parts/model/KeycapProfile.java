package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.parts.model.enums.SwitchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "KeycapProfile")
public class KeycapProfile {
    @Id
    @Column(name="idKeycapProfile")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="KeycapProfileName")
    private String keycapProfileName;
}
