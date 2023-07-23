package KeyboardShop.Keytopia.utils.model;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC"),
    UNSORTED("UNSORTED");
    private final String direction;

    SortDirection(String type) {
        this.direction = type;
    }

    public String getType() {
        return direction;
    }
}
