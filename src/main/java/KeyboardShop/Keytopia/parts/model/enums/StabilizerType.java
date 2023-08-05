package KeyboardShop.Keytopia.parts.model.enums;

public enum StabilizerType {
    CLAMPED("Clamped"),
    SCREW_IN("Screw-in"),
    NONE("None");
    private final String type;

    StabilizerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
