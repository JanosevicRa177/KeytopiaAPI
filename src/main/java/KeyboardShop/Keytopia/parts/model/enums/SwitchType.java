package KeyboardShop.Keytopia.parts.model.enums;

public enum SwitchType {
    CLICKY("Clicky"),
    TACTILE("Tactile"),
    LINEAR("Linear");
    private String type;

    SwitchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
