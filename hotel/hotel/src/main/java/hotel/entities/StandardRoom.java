package hotel.entities;

import hotel.base.AbstractRoom;
import hotel.base.ExtraServiceBase;

public class StandardRoom extends AbstractRoom implements ExtraServiceBase {
    private static final long serialVersionUID = 1L;

    public StandardRoom(int number, String description) {
        super(number, description);
    }

    @Override
    public double roomPrice(int days) {
        double finalCost = (STANDARD_RATE * days);
        System.out.println("- Final cost: " + (STANDARD_RATE * days));
        return finalCost;
    }

    @Override
    public void makePayment(double amount) {
       if (amount < STANDARD_RATE){
        System.out.println("Amount not yet paid!");
       } else {
        System.out.println("Amount paid!");
       }
    }
}
