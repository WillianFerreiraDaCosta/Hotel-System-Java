package hotel.menu;

import hotel.utils.ConsoleUI;
import hotel.utils.Keyboard;

public class MainMenu {

    private final RoomMenu roomMenu = new RoomMenu();
    private final GuestMenu guestMenu = new GuestMenu();
    private final ReservationMenu reservationMenu;

    public MainMenu() {
        this.reservationMenu = new ReservationMenu(guestMenu, roomMenu.getRoomCommands());
    }

    public void start() {
        int option;

        do {
            ConsoleUI.clearScreen();
            ConsoleUI.header("Hotel System");

            System.out.println("=== MAIN MENU ===");
            System.out.println("1 - Room Control");
            System.out.println("2 - Guests");
            System.out.println("3 - Reservations");
            System.out.println("0 - Exit");

            option = Keyboard.readInt("\nChoose:");

            switch (option) {
                case 1 -> roomMenu.show();
                case 2 -> guestMenu.show();
                case 3 -> reservationMenu.show();
                case 0 -> ConsoleUI.messageOk("Shutting down system...");
                default -> ConsoleUI.messageError("Invalid option!");
            }

            if (option != 0) {
                ConsoleUI.pause();
            }
        } while (option != 0);
    }
}