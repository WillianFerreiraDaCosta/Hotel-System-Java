package hotel.entities;

import hotel.base.AbstractRoom;
import hotel.base.ExtraServiceBase;

public class LuxuryRoom extends AbstractRoom implements ExtraServiceBase {
    private static final long serialVersionUID = 1L;

    public LuxuryRoom(int number, String description) {
        super(number, description);
    }

    @Override
    public double roomPrice(int days) {
        double finalCost = (LUXURY_RATE * days);
        System.out.println("- Final cost: " + (LUXURY_RATE * days));
        return finalCost;
    }

    @Override
    public void makePayment(double amount) {
        if (amount < LUXURY_RATE){
            System.out.println("Amount not yet paid!");
        } else {
            System.out.println("Amount paid!");
        }
    }
}
