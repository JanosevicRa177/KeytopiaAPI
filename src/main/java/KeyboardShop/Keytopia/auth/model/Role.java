package KeyboardShop.Keytopia.auth.model;

public enum Role {
    BUYER("BUYER"),
    ADMIN("ADMIN");
    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
