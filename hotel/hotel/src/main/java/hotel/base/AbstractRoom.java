package hotel.base;

import hotel.utils.UniqueIdGenerator;
import java.io.Serializable;

public abstract class AbstractRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private final long roomId;
    private int number;
    private RoomStatus status;
    private String description;


    public AbstractRoom(int number, String description) {
        this.roomId = UniqueIdGenerator.generateUniqueLongId();
        setNumber(number);
        status = RoomStatus.FREE;
        setDescription(description);
    }

    protected void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero.");
        }
        this.number = number;
    }

    protected void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public int getNumber() {
        return number;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public abstract double roomPrice(int daysReserved);
}