package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        CoffeeMachinePrinter printer = new CoffeeMachinePrinter("Delonghi");
        CoffeeMachineCalculator calculator = CoffeeMachineCalculator
                .create(200, 50, 15)
                .withPrinter(printer);
        CoffeeMachineEngine machine = CoffeeMachineEngine
                .create()
                .withParameters(400, 540, 120)
                .withCups(9)
                .withMoney(550)
                .withPrinter(printer)
                .withCalculator(calculator);

        Scanner scanner = getScanner();

        while(machine.getState() != CoffeeMachineEngine.State.EXIT) {
            machine.info();

            String command = askString(scanner);

            machine.exec(command);
        }

    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static String askString(Scanner scanner) {
        return scanner.next();
    }
}
