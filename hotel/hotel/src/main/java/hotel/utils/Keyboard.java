package hotel.utils;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Keyboard {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter an integer number.");
                scanner.nextLine();
            }
        }
    }

    public static int readInt(String message) {
        System.out.print(message + " ");
        return readInt();
    }

    public static String readString() {
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Empty input or buffer cleared! Enter again.");
        }
    }

    public static String readString(String message) {
        System.out.print(message + " ");
        return readString();
    }

    public static double readDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a decimal number.");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(String message) {
        System.out.print(message + " ");
        return readDouble();
    }

    public static LocalDate readDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String input = readString();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Use format YYYY-MM-DD.");
            }
        }
    }

    public static LocalDate readDate(String message) {
        System.out.print(message + " ");
        return readDate();
    }
}
