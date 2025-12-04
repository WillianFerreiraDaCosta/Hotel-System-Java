package hotel.controllers;

import hotel.entities.ReservationAction;
import hotel.entities.StandardRoom;
import hotel.entities.LuxuryRoom;
import hotel.base.AbstractRoom;
import hotel.utils.Keyboard;

public class RoomCommands {

    private RoomListController list = new RoomListController();

    public RoomListController getList() {
        return list;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n=== ROOM MENU ===");
            System.out.println("1 - Add room");
            System.out.println("2 - List rooms");
            System.out.println("3 - Occupy room");
            System.out.println("4 - Vacate room");
            System.out.println("5 - Set to cleaning");
            System.out.println("6 - Set to maintenance");
            System.out.println("0 - Exit");
            option = Keyboard.readInt("Choose: ");

            switch (option) {
                case 1 -> add();
                case 2 -> listRooms();
                case 3 -> changeStatus("occupy");
                case 4 -> changeStatus("vacate");
                case 5 -> changeStatus("clean");
                case 6 -> changeStatus("maintain");
                case 0 -> System.out.println("Closing...");
                default -> System.out.println("Invalid option!");
            }
        } while (option != 0);
    }

    public void add() {
        int number = Keyboard.readInt("Room number: ");
        String description = Keyboard.readString("Room description: ");
        System.out.println("Room type: 1 - Standard, 2 - Luxury");
        int type = Keyboard.readInt("Choose type: ");
        AbstractRoom r;
        if (type == 2) {
            r = new LuxuryRoom(number, description);
        } else {
            r = new StandardRoom(number, description);
        }
        list.addRoom(r);
        System.out.println("Room added successfully!");
    }

    public void listRooms() {
        list.listRooms();
    }

    public void listByStatus(String status) {
        list.listByStatus(status);
    }

    public AbstractRoom findRoomByNumber(int number) {
        return list.scanRooms(number);
    }

    public void changeStatus(String action) {
        int number = Keyboard.readInt("Room number: ");
        AbstractRoom r = list.scanRooms(number);

        if (r == null) {
            System.out.println("Room not found!");
            return;
        }

        ReservationAction reservation = new ReservationAction(r);

        switch (action) {
            case "occupy" -> reservation.occupy();
            case "vacate" -> reservation.vacate();
            case "clean" -> reservation.clean();
            case "maintain" -> reservation.maintenance();
            default -> System.out.println("Invalid action!");
        }
    }
}
