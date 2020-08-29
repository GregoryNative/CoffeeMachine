package machine;

import java.util.EnumMap;

public class CoffeeMachineCalculator {
    public static enum Attribute {
        WATER,
        MILK,
        COFFEE_BEANS
    }

    private CoffeeMachinePrinter printer;

    private int waterMl;
    private int milkMl;
    private int coffeeBeansG;

    public static CoffeeMachineCalculator create(int waterMl, int milkMl, int coffeeBeansG) {
        return new CoffeeMachineCalculator(waterMl, milkMl, coffeeBeansG);
    }

    public CoffeeMachineCalculator withPrinter(CoffeeMachinePrinter printer) {
        this.printer = printer;

        return this;
    }
    
    public CoffeeMachineCalculator(int waterMl, int milkMl, int coffeeBeansG) {
       this.waterMl = waterMl;
       this.milkMl = milkMl;
       this.coffeeBeansG = coffeeBeansG;
    }

    public EnumMap<Attribute, Integer> calculate(int cups) {
        EnumMap<Attribute, Integer> map = new EnumMap<>(Attribute.class);

        map.put(Attribute.WATER, cups * this.waterMl);
        map.put(Attribute.MILK, cups * this.milkMl);
        map.put(Attribute.COFFEE_BEANS, cups * this.coffeeBeansG);

        return map;
    }

    public void calculateAndPrint(int cups) {
        EnumMap<Attribute, Integer> map = calculate(cups);

        this.printer.print("For " + cups + " cups of coffee you will need:");
        this.printer.print(map.get(Attribute.WATER) + " ml of water");
        this.printer.print(map.get(Attribute.MILK) + " ml of milk");
        this.printer.print(map.get(Attribute.COFFEE_BEANS) + " g of coffee beans");
    }

    public void promptNeededForCups() {
        this.printer.print("Write how many cups of coffee you will need:");

        int countOfCups = this.printer.askNumber();

        calculateAndPrint(countOfCups);
    }

    public boolean calculateIfEnoughResources(CoffeeMachine coffeeMachine, Coffee coffee) {
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

    // Getters
    public int getWaterMl() {
        return waterMl;
    }

    public int getMilkMl() {
        return milkMl;
    }

    public int getCoffeeBeansG() {
        return coffeeBeansG;
    }
}