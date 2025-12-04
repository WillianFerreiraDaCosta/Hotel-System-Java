package hotel.menu;

import hotel.controllers.RoomCommands;
import hotel.utils.ConsoleUI;
import hotel.utils.Keyboard;

public class RoomMenu {

    private final RoomCommands commands = new RoomCommands();

    public void show() {
        int option;

        do {
            ConsoleUI.clearScreen();
            ConsoleUI.header("Room Menu");
            System.out.println("\n=== ROOM MENU ===");
            System.out.println("1 - Add room");
            System.out.println("2 - List rooms");
            System.out.println("3 - Set to cleaning");
            System.out.println("4 - Set to maintenance");
            System.out.println("0 - Exit");

            option = Keyboard.readInt("\nChoose:");

            ConsoleUI.separator();

            switch (option) {
                case 1 -> commands.add();
                case 2 -> commands.listRooms();
                case 3 -> commands.changeStatus("clean");
                case 4 -> commands.changeStatus("maintain");
                case 0 -> ConsoleUI.messageInfo("Returning to main menu...");
                default -> ConsoleUI.messageError("Invalid option!");
            }

            if (option != 0) {
                ConsoleUI.pause();
            }

        } while (option != 0);
    }
    public RoomCommands getRoomCommands() {
        return commands;
    }
}