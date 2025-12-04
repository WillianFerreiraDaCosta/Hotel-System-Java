package hotel.controllers;

import hotel.entities.Guest;
import hotel.exceptions.InvalidTaxIdException;
import hotel.persistence.Persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuestController {

    public static class GuestRecord implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Guest guest;
        private final String name;
        private final String taxId;
        private final String phone;
        private final String email;

        public GuestRecord(Guest guest, String name, String taxId, String phone, String email) {
            this.guest = guest;
            this.name = name;
            this.taxId = taxId;
            this.phone = phone;
            this.email = email;
        }

        public Guest getGuest() {
            return guest;
        }

        public String getName() {
            return name;
        }

        public String getTaxId() {
            return taxId;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "GuestRecord{" +
                    "name='" + name + '\'' +
                    ", taxId='" + taxId + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    private final List<GuestRecord> records = new ArrayList<>();
    private static final String GUESTS_FILE = "guests.dat";

    public GuestController() {
        load();
    }

    public boolean addGuest(String name, String taxId, String phone, String email) throws InvalidTaxIdException {
        if (findByTaxId(taxId) != null) return false;
        Guest g = new Guest(name, taxId, phone, email);
        GuestRecord reg = new GuestRecord(g, name, taxId, phone, email);
        records.add(reg);
        save();
        return true;
    }

    public GuestRecord findByTaxId(String taxId) {
        for (GuestRecord r : records) {
            if (r.getTaxId().equals(taxId)) return r;
        }
        return null;
    }

    public List<GuestRecord> listGuests() {
        return new ArrayList<>(records);
    }

    private void save() {
        Persistence.save(GUESTS_FILE, records);
    }

    @SuppressWarnings("unchecked")
    private void load() {
        ArrayList<GuestRecord> loaded = (ArrayList<GuestRecord>) (ArrayList<?>) 
            Persistence.load(GUESTS_FILE, new ArrayList<GuestRecord>());
        records.clear();
        if (loaded != null && !loaded.isEmpty()) {
            records.addAll(loaded);
        }
    }
}
