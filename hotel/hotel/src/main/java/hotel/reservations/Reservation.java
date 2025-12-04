package hotel.reservations;

import hotel.controllers.GuestController.GuestRecord;
import hotel.base.AbstractRoom;
import hotel.base.RoomStatus;
import hotel.utils.UniqueIdGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String reservationId;
    private final GuestRecord guestRecord;
    private final AbstractRoom room;
    private final LocalDate entryDate;
    private final int daysReserved;
    private final List<ExtraService> services = new ArrayList<>();
    private boolean ended = false;
    private LocalDate exitDate = null;

    public Reservation(GuestRecord guestRecord, AbstractRoom room,
                           LocalDate entryDate, int daysReserved) {

        this.reservationId = UniqueIdGenerator.generateUniqueStringId();
        this.guestRecord = guestRecord;
        this.room = room;
        this.entryDate = entryDate;
        this.daysReserved = Math.max(1, daysReserved);
    }

    public String getReservationId() {
        return reservationId;
    }

    public GuestRecord getGuestRecord() {
        return guestRecord;
    }

    public AbstractRoom getRoom() {
        return room;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public int getDaysReserved() {
        return daysReserved;
    }

    public List<ExtraService> getServices() {
        return new ArrayList<>(services);
    }

    public void addService(ExtraService s) {
        services.add(s);
    }

    public void finishReservation() {
        this.exitDate = entryDate.plusDays(daysReserved);
        this.ended = true;
        room.setStatus(RoomStatus.FREE);
    }

    public double calculateTotal() {
        double totalRoom = room.roomPrice(daysReserved);
        double totalServices = services.stream().mapToDouble(ExtraService::getPrice).sum();
        return totalRoom + totalServices;
    }

    public boolean isEnded() {
        return ended;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + reservationId + '\'' +
                ", guest=" + guestRecord.getName() +
                ", taxId=" + guestRecord.getTaxId() +
                ", room=" + room.getNumber() +
                ", entry=" + entryDate +
                ", days=" + daysReserved +
                ", services=" + services +
                ", totalEstimated=$" + String.format("%.2f", calculateTotal()) +
                ", ended=" + ended +
                '}';
    }
}
