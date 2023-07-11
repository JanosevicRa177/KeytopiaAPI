package KeyboardShop.Keytopia.parts.model.enums;

public enum CableConnector {
    USB_C("USB_C"),
    USB("USB");
    private final String connectorType;

    CableConnector(String connectorType) {
        this.connectorType = connectorType;
    }

    public String getConnectorType() {
        return connectorType;
    }
}
