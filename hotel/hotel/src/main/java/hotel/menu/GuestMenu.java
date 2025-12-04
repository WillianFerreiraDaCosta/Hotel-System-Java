package hotel.menu;

import hotel.controllers.GuestController;
import hotel.utils.ConsoleUI;
import hotel.utils.Keyboard;
import hotel.exceptions.InvalidTaxIdException;

public class GuestMenu {

    private final GuestController controller = new GuestController();

    public void show() {
        int option;
        do {
            ConsoleUI.clearScreen();
            ConsoleUI.header("Guests");
            System.out.println("1 - Register guest");
            System.out.println("2 - List guests");
            System.out.println("0 - Back");

            option = Keyboard.readInt("\nChoose:");

            switch (option) {
                case 1 -> register();
                case 2 -> listGuests();
                case 0 -> ConsoleUI.messageInfo("Returning...");
                default -> ConsoleUI.messageError("Invalid option!");
            }

            if (option != 0) ConsoleUI.pause();
        } while (option != 0);
    }

    private void register() {
        ConsoleUI.header("Guest Registration");
        try {
            String name = Keyboard.readString("Name:");
            String taxId = Keyboard.readString("Tax ID:");
            String phone = Keyboard.readString("Phone:");
            String email = Keyboard.readString("Email:");
            boolean ok = controller.addGuest(name, taxId, phone, email);
            if (ok) ConsoleUI.messageOk("Guest registered successfully!");
            else ConsoleUI.messageAlert("Tax ID already registered.");
        } catch (InvalidTaxIdException e) {
            ConsoleUI.messageError("Invalid Tax ID: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            ConsoleUI.messageError("Error: " + ex.getMessage());
        }
    }

    private void listGuests() {
        ConsoleUI.header("Guest List");
        for (GuestController.GuestRecord r : controller.listGuests()) {
            System.out.println(r);
        }
    }

    public GuestController getController() {
        return controller;
    }
}