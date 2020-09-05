package machine;

import java.util.Scanner;

public class CoffeeMachinePrinter {
    private String name;

    public CoffeeMachinePrinter(String name) {
        this.name = name;
    }

    public void print(String str) {
        // String message = name + ": " + str;
        String message = str;

        System.out.println(message);
    }

    public void divider() {
        System.out.println();
    }

    public int askNumber() {
        Scanner scan = new Scanner(System.in);

        return scan.nextInt();
    }

    public String askString() {
        Scanner scan = new Scanner(System.in);

        return scan.next();
    }
}
