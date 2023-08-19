package KeyboardShop.Keytopia.parts.model.enums;

public enum PartType {
    CABLE("Cable"),
    KEYCAP("Keycap"),
    KEYCAP_SET("Keycap set"),
    PCB("PCB"),
    PLATE("Plate"),
    STABILIZER("Stabilizer"),
    SWITCH_SET("Switch set"),
    CASE("Case"),
    KEYBOARD("Keyboard");
    private final String type;

    PartType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
