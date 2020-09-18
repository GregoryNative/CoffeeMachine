package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = getScanner();
        print("Enter cells: ");

        String initial = scanner.next();

        Game game = new Game(initial, scanner);

        game.start();
    }


    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static String askString(Scanner scanner) {
        return scanner.next();
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String str) {
        System.out.println(str);
    }
}
