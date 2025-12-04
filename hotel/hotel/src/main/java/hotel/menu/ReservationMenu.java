package hotel.menu;

import hotel.controllers.GuestController;
import hotel.controllers.ReservationController;
import hotel.controllers.RoomCommands;
import hotel.reservations.ExtraService;
import hotel.reservations.Reservation;
import hotel.utils.ConsoleUI;
import hotel.utils.Keyboard;
import hotel.controllers.GuestController.GuestRecord;

import hotel.base.AbstractRoom;
import hotel.entities.StandardRoom;
import hotel.entities.LuxuryRoom;
import hotel.base.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationMenu {

    private final GuestMenu guestMenu;
    private final RoomCommands roomCommands;
    private final ReservationController reservationController = new ReservationController();

    public ReservationMenu(GuestMenu guestMenu, RoomCommands roomCommands) {
        this.guestMenu = guestMenu;
        this.roomCommands = roomCommands;
    }

    public void show() {
        int option;
        do {
            ConsoleUI.clearScreen();
            ConsoleUI.header("Reservations");
            System.out.println("1 - Book Room");
            System.out.println("2 - List active reservations");
            System.out.println("3 - Finalize reservation (checkout) and pay");
            System.out.println("0 - Back");

            option = Keyboard.readInt("\nChoose:");

            switch (option) {
                case 1 -> reservationFlow();
                case 2 -> listActive();
                case 3 -> finalizeReservation();
                case 0 -> ConsoleUI.messageInfo("Returning...");
                default -> ConsoleUI.messageError("Invalid option!");
            }

            if (option != 0) ConsoleUI.pause();
        } while (option != 0);
    }

    private void reservationFlow() {
        ConsoleUI.header("Book Room");
        GuestController controller = guestMenu.getController();

        String taxId = Keyboard.readString("Enter registered guest Tax ID:");
        GuestRecord record = controller.findByTaxId(taxId);
        if (record == null) {
            ConsoleUI.messageError("Guest not found. Register in Guests menu first.");
            return;
        }

        ConsoleUI.messageInfo("Free rooms:");

        ArrayList<AbstractRoom> freeRooms = roomCommands
                .getList()
                .getFreeRooms();

        if (freeRooms.isEmpty()) {
            ConsoleUI.messageError("No free rooms available.");
            ConsoleUI.messageInfo("Add rooms in 'Rooms' menu first.");
            return;
        }

        System.out.println("=== FREE ROOMS ===");
        for (AbstractRoom r : freeRooms) {
            String type;
            if (r instanceof LuxuryRoom) {
                type = "Luxury";
            } else if (r instanceof StandardRoom) {
                type = "Standard";
            } else {
                type = "Unknown";
            }

            System.out.println(
                    "Room number: " + r.getNumber() +
                            " - Type: " + type +
                            " - Description: " + r.getDescription() +
                            " - Status: " + r.getStatus()
            );
        }

        int number = Keyboard.readInt("Choose room number:");

        AbstractRoom chosenRoom = null;
        for (AbstractRoom r : freeRooms) {
            if (r.getNumber() == number) {
                chosenRoom = r;
                break;
            }
        }

        if (chosenRoom == null) {
            ConsoleUI.messageError("Room not found or not free.");
            return;
        }

        LocalDate entry = Keyboard.readDate("Entry date (YYYY-MM-DD):");
        int days = Keyboard.readInt("Number of days:");

        ConsoleUI.messageInfo("Include Breakfast? ($ " + ExtraService.BREAKFAST.getPrice() + ")");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        int op = Keyboard.readInt("Choose:");

        Reservation reservation = new Reservation(record, chosenRoom, entry, days);

        if (op == 1) {
            reservation.addService(ExtraService.BREAKFAST);
            ConsoleUI.messageOk("Breakfast added.");
        }

        chosenRoom.setStatus(RoomStatus.OCCUPIED);
        reservationController.addReservation(reservation);
        ConsoleUI.messageOk("Reservation created. ID: " + reservation.getReservationId());
        System.out.println(reservation);
    }

    private void listActive() {
        ConsoleUI.header("Active Reservations");
        List<Reservation> active = reservationController.listActiveReservations();
        if (active.isEmpty()) {
            ConsoleUI.messageInfo("No active reservations.");
            return;
        }
        for (Reservation r : active) {
            System.out.println(r);
        }
    }

    private void finalizeReservation() {
        ConsoleUI.header("Finalize Reservation (checkout)");
        var active = reservationController.listActiveReservations();
        if (active.isEmpty()) {
            ConsoleUI.messageInfo("No active reservations.");
            return;
        }
        for (int i = 0; i < active.size(); i++) {
            System.out.println((i + 1) + " - " +
                    active.get(i).getReservationId() + " - " +
                    active.get(i).getGuestRecord().getName());
        }
        int sel = Keyboard.readInt("Choose reservation (number):");
        if (sel < 1 || sel > active.size()) {
            ConsoleUI.messageError("Invalid selection.");
            return;
        }
        Reservation r = active.get(sel - 1);
        r.finishReservation();
        double total = r.calculateTotal();
        ConsoleUI.messageOk("Reservation finalized. Total to pay: $ " + String.format("%.2f", total));
    }
}