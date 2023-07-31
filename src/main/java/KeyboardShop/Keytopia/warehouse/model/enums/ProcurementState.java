package KeyboardShop.Keytopia.warehouse.model.enums;

public enum ProcurementState {
    REALIZED("Realized"),
    PENDING("Pending"),
    CANCELED("Canceled"),
    NONE("None");
    private final String state;

    ProcurementState(String state) {
        this.state = state;
    }

    public String getType() {
        return state;
    }
}
