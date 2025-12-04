package hotel.base;

public interface ExtraServiceBase {

    public static final double STANDARD_RATE = 450.00;
    public static final double LUXURY_RATE = 610.00;

    public double roomPrice(int days);

    public void makePayment(double amount);
}