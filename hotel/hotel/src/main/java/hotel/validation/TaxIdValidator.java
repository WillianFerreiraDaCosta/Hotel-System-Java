package hotel.validation;
import java.util.ArrayList;

import hotel.exceptions.InvalidTaxIdException;

public class TaxIdValidator {
    
    private static ArrayList<String> taxIds = new ArrayList<>();

    public static void validate(String taxId) throws InvalidTaxIdException {
        if (taxId == null || taxId.trim().isEmpty()){
            throw new InvalidTaxIdException("Tax ID cannot be empty!");
        }
        if (taxId.length() != 11) {
            throw new InvalidTaxIdException("Tax ID must contain 11 digits!");
        }

        if (taxIds.contains(taxId)) {
            throw new InvalidTaxIdException("Tax ID already registered!");
        }

        taxIds.add(taxId);
    }
}
