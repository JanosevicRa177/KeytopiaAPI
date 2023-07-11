package KeyboardShop.Keytopia.parts.model.enums;

public enum PriceWeight {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy");
    
    private final String priceWeight;

    PriceWeight(String priceWeight) {
        this.priceWeight = priceWeight;
    }

    public String getPriceWeight() {
        return priceWeight;
    }
}
