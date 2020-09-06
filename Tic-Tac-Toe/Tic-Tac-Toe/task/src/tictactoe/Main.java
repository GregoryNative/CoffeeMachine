package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = getScanner();
        System.out.print("Enter cells: ");
        String initial = scanner.next();

        Game game = new Game(initial);

        game.start();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static String askString(Scanner scanner) {
        return scanner.next();
    }
}
