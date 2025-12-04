package hotel.utils;

public class ConsoleUI {

    private static final int SCREEN_WIDTH = 100;

    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void header(String title) {
        clearScreen();
        final int totalWidth = SCREEN_WIDTH; 
        String upperTitle = title.toUpperCase();
        int spaces = (totalWidth - upperTitle.length()) / 2;

        String line = "=".repeat(totalWidth);
        String leftSpaces = " ".repeat(spaces);

        System.out.println(BOLD + line + RESET);
        System.out.println(leftSpaces + upperTitle);
        System.out.println(BOLD + line + "\n" + RESET);
    }

    public static void footer(String text) {
        final int totalWidth = SCREEN_WIDTH; 
        int spaces = (totalWidth - text.length()) / 2;

        String line = "=".repeat(totalWidth);
        String leftSpaces = " ".repeat(spaces);

        System.out.println("\n" + BOLD + line + RESET);
        System.out.println(leftSpaces + text);
        System.out.println(BOLD + line + RESET);
    }

    public static void separator(int width) {
        if (width > 0) {
            String line = "-".repeat(width);
            System.out.println(line);
        }
    }

    public static void separator() {
        separator(SCREEN_WIDTH);
    }

    public static void pause() {
        try {
            System.out.println("\nPress ENTER to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeSlowly(String text, int delayMs) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void progressBar(int total, int delayMs) {
        for (int i = 0; i <= total; i++) {
            String bar = "[" + "#".repeat(i) + " ".repeat(total - i) + "]";
            System.out.print("\r" + bar + " " + (i * 100 / total) + "%");
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void message(String msg){
        System.out.println(msg);
    }

    public static void messageOk(String msg) {
        System.out.println(GREEN + "[OK] " + msg + RESET);
    }

    public static void messageError(String msg) {
        System.out.println(RED + "[ERROR] " + msg + RESET);
    }

    public static void messageAlert(String msg) {
        System.out.println(YELLOW + "[ALERT] " + msg + RESET);
    }

    public static void messageInfo(String msg) {
        System.out.println(BLUE + "[INFO] " + msg + RESET);
    }
}
