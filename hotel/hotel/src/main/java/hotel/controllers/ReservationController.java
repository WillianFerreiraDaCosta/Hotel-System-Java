package hotel.controllers;

import hotel.reservations.Reservation;
import hotel.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();
    private static final String RESERVATIONS_FILE = "reservations.dat";

    public ReservationController() {
        load();
    }

    public void addReservation(Reservation r) {
        reservations.add(r);
        save();
    }

    public List<Reservation> listActiveReservations() {
        List<Reservation> out = new ArrayList<>();
        for (Reservation r : reservations) {
            if (!r.isEnded()) out.add(r);
        }
        return out;
    }

    public List<Reservation> listAll() {
        return new ArrayList<>(reservations);
    }

    public Reservation findById(String id) {
        for (Reservation r : reservations) {
            if (r.getReservationId().equals(id)) return r;
        }
        return null;
    }

    private void save() {
        Persistence.save(RESERVATIONS_FILE, reservations);
    }

    @SuppressWarnings("unchecked")
    private void load() {
        ArrayList<Reservation> loaded = (ArrayList<Reservation>) (ArrayList<?>) 
            Persistence.load(RESERVATIONS_FILE, new ArrayList<Reservation>());
        reservations.clear();
        if (loaded != null && !loaded.isEmpty()) {
            reservations.addAll(loaded);
        }
    }
}
