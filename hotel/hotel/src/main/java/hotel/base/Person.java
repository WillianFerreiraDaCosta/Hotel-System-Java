package hotel.base;

import hotel.exceptions.InvalidTaxIdException;
import hotel.utils.UniqueIdGenerator;
import hotel.validation.TaxIdValidator;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String taxId;
    private String phone;
    private String email;
    private final String personId;

    protected Person(String name, String taxId, String phone, String email) throws InvalidTaxIdException {
        this.personId = UniqueIdGenerator.generateUniqueStringId();
        setName(name);
        setPhone(phone);
        setEmail(email);
        TaxIdValidator.validate(taxId);
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        } if (name.length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters.");
        }
        this.name = name;
    }

    protected void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty.");
        } if (phone.length() < 8) {
            throw new IllegalArgumentException("Phone must have at least 8 characters.");
        }
        this.phone = phone;
    }

    protected void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        } if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must be a valid address.");
        }
        this.email = email;
    }

    protected void testMain() {
        System.out.println("name: " + name + "\ntaxId: " + taxId + "\nphone: " + phone + "\nemail: " + email + "\npersonId: " + personId);
    }
}
