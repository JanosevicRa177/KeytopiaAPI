package KeyboardShop.Keytopia.parts.model.enums;

public enum StabilizerSize {
    U2("2u"),
    U7("7u"),
    U6_25("6.25u");
    private final String size;

    StabilizerSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
