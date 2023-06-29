package KeyboardShop.Keytopia.parts.model;

import KeyboardShop.Keytopia.parts.model.enums.CableConnector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Cable")
public class Cable extends Part {
    @Column(name="CableMaterial")
    private String material;
    @Column(name="CableColor")
    private String color;
    @Column(name="CableLength")
    private String length;
    @Column(name="CableConnectorKeyboard")
    private CableConnector keyboardConnector;
    @Column(name="CableConnectorComputer")
    private CableConnector computerConnector;
    @Column(name="IsCoiled")
    private boolean isCoiled;
    @Column(name="IsQuickRelease")
    private boolean isQuickRelease;
}
