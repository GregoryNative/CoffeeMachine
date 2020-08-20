package machine;

public class Application {
    public static void run() {
        CoffeeMachinePrinter printer = new CoffeeMachinePrinter("Delonghi");
        CoffeeMachineCalculator calculator = CoffeeMachineCalculator
                .create(200, 50, 15)
                .withPrinter(printer);
        CoffeeMachine machine = CoffeeMachine
                .create()
                .withParameters(400, 540, 120)
                .withCups(9)
                .withMoney(550)
                .withPrinter(printer)
                .withCalculator(calculator);

        machine.powerOn();
    }
}
