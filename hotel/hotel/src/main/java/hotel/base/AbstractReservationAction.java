package hotel.base;

public abstract class AbstractReservationAction {

    private AbstractRoom room;

    public AbstractReservationAction(AbstractRoom room) {
        this.room = room;
    }

    public void occupy() {
        if (room == null) {
            throw new IllegalArgumentException("Reservation without associated room...");
        }

        if (room.getStatus() == RoomStatus.FREE) {
            room.setStatus(RoomStatus.OCCUPIED);
            System.out.println("Room: " + room.getNumber() + " was occupied.");
        } else {
            System.out.println("Room: " + room.getNumber() + " is already occupied...");
        }
    }

    public void vacate() {
        if (room == null) {
            throw new IllegalArgumentException("Reservation without associated room...");
        }

        if (room.getStatus() == RoomStatus.OCCUPIED) {
            room.setStatus(RoomStatus.FREE);
            System.out.println("Room: " + room.getNumber() + " was vacated.");
        } else {
            System.out.println("The room is already free.");
        }
    }

    public void clean() {
        room.setStatus(RoomStatus.CLEANING);
    }

    public void maintenance() {
        room.setStatus(RoomStatus.MAINTENANCE);
    }
}