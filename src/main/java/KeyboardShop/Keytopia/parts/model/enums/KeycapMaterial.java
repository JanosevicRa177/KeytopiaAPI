package KeyboardShop.Keytopia.parts.model.enums;

public enum KeycapMaterial {
    ABS("ABS"),
    PBT("PBT"),
    DOUBLESHOT_PBT("Doubleshot PBT"),
    DOUBLESHOT_ABS("Doubleshot ABS");
    private final String keycapMaterial;

    KeycapMaterial(String keycapMaterial) {
        this.keycapMaterial = keycapMaterial;
    }

    public String getKeycapMaterial() {
        return keycapMaterial;
    }
}
