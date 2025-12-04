package hotel.entities;

import hotel.exceptions.InvalidTaxIdException;
import hotel.base.Person;

public class Guest extends Person {
    public Guest(String name, String taxId, String phone, String email) throws InvalidTaxIdException {
        super(name, taxId, phone, email);
    }

    public void testMain() {
        super.testMain();
    }
}
