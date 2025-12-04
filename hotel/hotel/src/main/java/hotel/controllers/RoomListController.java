package hotel.controllers;

import hotel.persistence.Persistence;

import java.util.ArrayList;

import hotel.base.AbstractRoom;
import hotel.base.RoomStatus;

public class RoomListController {
    private ArrayList<AbstractRoom> rooms = new ArrayList<>();
    private static final String ROOMS_FILE = "rooms.dat";

    public RoomListController() {
        load();
    }

    public void addRoom(AbstractRoom room){
        rooms.add(room);
        save();
    }

    public void listRooms(){
        for(AbstractRoom r: rooms){
            System.out.println("Room number: " + r.getNumber() + " - Description: " + r.getDescription() + " - Status: " + r.getStatus());
        }
    }

    public AbstractRoom scanRooms(int number){
        for(AbstractRoom r: rooms){
            if (r.getNumber() == number){
                return r;
            }
        }
        System.out.println("Room not found");
        return null;
    }

    public void listByStatus(String status){
        for(AbstractRoom r: rooms){
            if(r.getStatus().toString().equalsIgnoreCase(status)) {
                System.out.println("Room number: " + r.getNumber() + " - Description: " + r.getDescription() + " - Status: " + r.getStatus());
            }
        }
    }

    public ArrayList<AbstractRoom> getFreeRooms() {
        ArrayList<AbstractRoom> free = new ArrayList<>();
        for(AbstractRoom r: rooms) {
            if(r.getStatus() == RoomStatus.FREE) {
                free.add(r);
            }
        }
        return free;
    }

    public ArrayList<AbstractRoom> getRooms() {
        return new ArrayList<>(rooms);
    }

    public void update(AbstractRoom room) {
        save();
    }

    private void save() {
        Persistence.save(ROOMS_FILE, rooms);
    }

    @SuppressWarnings("unchecked")
    private void load() {
        ArrayList<AbstractRoom> loaded = (ArrayList<AbstractRoom>) (ArrayList<?>) 
            Persistence.load(ROOMS_FILE, new ArrayList<AbstractRoom>());
        rooms = loaded != null ? loaded : new ArrayList<>();
    }
}
