package hotel.persistence;

import java.io.*;
import java.util.List;

public class Persistence {

    public static <T> void save(String path, List<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> load(String path, List<T> emptyList) {
        File file = new File(path);

        if (!file.exists()) {
            return emptyList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
            return emptyList;
        }
    }
}
