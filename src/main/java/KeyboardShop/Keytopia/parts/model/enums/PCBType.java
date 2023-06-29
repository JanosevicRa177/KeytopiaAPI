package KeyboardShop.Keytopia.parts.model.enums;

public enum PCBType {
    HOT_SWAP("Hot-swap"),
    STANDARD("Standard");
    private String type;

    PCBType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
