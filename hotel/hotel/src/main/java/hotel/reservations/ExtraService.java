package hotel.reservations;

public enum ExtraService {
    BREAKFAST("Breakfast", 25.00);

    private final String description;
    private final double price;

    ExtraService(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return description + " ($ " + String.format("%.2f", price) + ")";
    }
}
