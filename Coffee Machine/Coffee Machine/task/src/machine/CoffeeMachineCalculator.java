package machine;

public class CoffeeMachineCalculator {
    private CoffeeMachinePrinter printer;

    public CoffeeMachineCalculator withPrinter(CoffeeMachinePrinter printer) {
        this.printer = printer;

        return this;
    }

    public boolean calculateIfEnoughResources(CoffeeMachineEngine coffeeMachine, Coffee coffee) {
        int currentCups = coffeeMachine.getCups();
        if (currentCups - 1 < 0) {
            this.printer.print("Sorry, not enough cups!");
            return false;
        }

        int currentWater = coffeeMachine.getWaterMl() - coffee.getWater();
        if (currentWater < 0) {
            this.printer.print("Sorry, not enough water!");
            return false;
        }

        int currentMilk = coffeeMachine.getMilkMl() - coffee.getMilk();
        if (currentMilk < 0) {
            this.printer.print("Sorry, not enough milk!");
            return false;
        }

        int currentCoffeeBeans = coffeeMachine.getCoffeeBeansG() - coffee.getCoffee();
        if (currentCoffeeBeans < 0) {
            this.printer.print("Sorry, not enough coffee beans!");
            return false;
        }

        return true;
    }
}