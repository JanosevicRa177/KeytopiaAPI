package KeyboardShop.Keytopia.parts.model.enums;

public enum PinType {
    PIN3("3PIN"),
    PIN5("5PIN");
    private final String pinType;

    PinType(String pinType) {
        this.pinType = pinType;
    }

    public String getPinType() {
        return pinType;
    }
}
