package machine;

import java.util.EnumMap;

public class CoffeeMachine {
    public static void main(String[] args) {
        Application.run();
    }

    public static enum Action {
        BUY("buy"),
        FILL("fill"),
        TAKE("take"),
        REMAINING("remaining"),
        EXIT("exit");

        private final String stringValue;
        Action(final String s) { stringValue = s; }
        public String toString() { return stringValue; }
    }

    // DI
    private CoffeeMachinePrinter printer;
    private CoffeeMachineCalculator calculator;

    // parameters
    private int waterMl;
    private int milkMl;
    private int coffeeBeansG;

    private int cups;
    private int money;

    private boolean isExitStarted = false;

    public static CoffeeMachine create() {
        return new CoffeeMachine();
    }

    public CoffeeMachine() {
        this.waterMl = 0;
        this.milkMl = 0;
        this.coffeeBeansG = 0;
    }

    public CoffeeMachine(int waterMl, int milkMl, int coffeeBeansG) {
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansG = coffeeBeansG;
    }

    public CoffeeMachine withParameters(int waterMl, int milkMl, int coffeeBeansG) {
        return new CoffeeMachine(waterMl, milkMl, coffeeBeansG);
    }


    public CoffeeMachine withCups(int cups) {
        this.cups = cups;

        return this;
    }

    public CoffeeMachine withMoney(int money) {
        this.money = money;

        return this;
    }

    public CoffeeMachine withPrinter(CoffeeMachinePrinter printer) {
        this.printer = printer;

        return this;
    }

    public CoffeeMachine withCalculator(CoffeeMachineCalculator calculator) {
        this.calculator = calculator;

        return this;
    }

    // PRIVATE COFFEE MAKER METHODS
    private void start() {
        print("Starting to make a coffee");
    }

    private void grind() {
        print("Grinding coffee beans");
    }

    private void boil() {
        print("Boiling water");
    }

    private void mix() {
        print("Mixing boiled water with crushed coffee beans");
    }

    private void pourCoffee() {
        print("Pouring coffee into the cup");
    }

    private void pourMilk() {
        print("Pouring some milk into the cup");
    }

    private void finish() {
        print("Coffee is ready!");
    }

    private void print(String str) {
        this.printer.print(str);
    }

    public void makeCoffee() {
        this.start();
        this.grind();
        this.boil();
        this.mix();
        this.pourCoffee();
        this.pourMilk();
        this.finish();
    }

    public void promptNeededForCups() {
        this.calculator.promptNeededForCups();
    }

    public void promptIsEnoughForCups() {
        this.promptAndSetWater();
        this.promptAndSetMilk();
        this.promptAndSetCoffeeBeans();
        this.promptHowManyCupsNeeded();
    }

    public void promptAndSetWater() {
        this.printer.print("Write how many ml of water the coffee machine has:");

        int waterMl = this.printer.askNumber();

        setWaterMl(waterMl);
    }

    public void promptAndSetMilk() {
        this.printer.print("Write how many ml of milk the coffee machine has:");

        int milkMl = this.printer.askNumber();

        setMilkMl(milkMl);
    }

    public void promptAndSetCoffeeBeans() {
        this.printer.print("Write how many ml of coffee beans the coffee machine has:");

        int coffeeBeansG = this.printer.askNumber();

        setCoffeeBeansG(coffeeBeansG);
    }

    public void promptHowManyCupsNeeded() {
        this.printer.print("Write how many cups of coffee you will need:");

        int cups = this.printer.askNumber();
        EnumMap<CoffeeMachineCalculator.Attribute, Integer> map = this.calculator.calculate(cups);

        int waterLeft = this.waterMl;
        int milkLeft = this.milkMl;
        int coffeeBeansLeft = this.coffeeBeansG;

        int count = 0;
        while(true) {
            waterLeft -= this.calculator.getWaterMl();
            milkLeft -= this.calculator.getMilkMl();
            coffeeBeansLeft -= this.calculator.getCoffeeBeansG();

            if (waterLeft < 0 || milkLeft < 0 || coffeeBeansLeft < 0) {
                break;
            }

            count++;
        };

        if (count < cups) {
            this.printer.print("No, I can make only " + String.valueOf(count) + " cup(s) of coffee");
            return;
        }

        if (count > 0 && count == cups) {
            this.printer.print("Yes, I can make that amount of coffee");
            return;
        }

        if (count > cups) {
            this.printer.print("Yes, I can make that amount of coffee (and even " + String.valueOf(count - cups) + " more than that)");
            return;
        }
    }

    public void startLoop() {
        while(true) {
            this.powerOn();

            if (isExitStarted) {
                break;
            }
        }
    }

    public void powerOn() {
        String welcomeMessage = String.format(
            "Write action (%s, %s, %s, %s, %s):",
            Action.BUY, Action.FILL, Action.TAKE, Action.REMAINING, Action.EXIT
        );
        this.printer.print(welcomeMessage);

        String action = this.printer.askString();

        switch (Action.valueOf(action.toUpperCase())) {
            case BUY:
                this.buyAction();
                break;
            case FILL:
                this.fillAction();
                break;
            case TAKE:
                this.takeAction();
                break;
            case REMAINING:
                this.remainingAction();
                break;
            case EXIT:
                this.exitAction();
                break;
        }
    }

    private void printInfo() {
        this.printer.divider();
        this.printer.print("The coffee machine has:");
        this.printer.print(String.valueOf(this.waterMl) + " of water");
        this.printer.print(String.valueOf(this.milkMl) + " of milk");
        this.printer.print(String.valueOf(this.coffeeBeansG) + " of coffee beans");
        this.printer.print(String.valueOf(this.cups) + " of disposable cups");
        this.printer.print("$" + String.valueOf(this.money) + " of money");
        this.printer.divider();
    }

    private void buyAction() {
        this.printer.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");

        String coffeeTypeStr = this.printer.askString();

        if (coffeeTypeStr.equals("back")) {

            return;
        }

        int coffeeType = Integer.valueOf(coffeeTypeStr);
        if (coffeeType > 0 && coffeeType < 4) {
            Coffee coffee = CoffeeFactory.create(coffeeType);

            if (this.calculator.calculateIfEnoughResources(this, coffee)) {
                this.printer.print("I have enough resources, making you a coffee!");
                this.makeCoffeeAuto(coffee);
            }

            return;
        }

        this.buyAction();
    }

    private void fillAction() {
        this.printer.print("Write how many ml of water do you want to add:");
        int water = this.printer.askNumber();
        this.setWaterMl(this.getWaterMl() + water);

        this.printer.print("Write how many ml of milk do you want to add:");
        int milk = this.printer.askNumber();
        this.setMilkMl(this.getMilkMl() + milk);

        this.printer.print("Write how many grams of coffee beans do you want to add:");
        int coffee = this.printer.askNumber();
        this.setCoffeeBeansG(this.getCoffeeBeansG() + coffee);

        this.printer.print("Write how many disposable cups of coffee do you want to add:");
        int cups = this.printer.askNumber();
        this.setCups(this.getCups() + cups);
    }

    private void takeAction() {
        int currentMoney = this.getMoney();
        this.printer.print("I gave you $" + currentMoney);

        this.setMoney(0);
    }

    private void remainingAction() {
        this.printInfo();
    }

    private void exitAction() {
        isExitStarted = true;
    }

    public void makeCoffeeAuto(Coffee coffee) {
        int water = this.waterMl;
        int milk = this.milkMl;
        int coffeeBeans = this.coffeeBeansG;
        int cups = this.cups;
        int money = this.money;

        this.setWaterMl(water - coffee.getWater());
        this.setMilkMl(milk - coffee.getMilk());
        this.setCoffeeBeansG(coffeeBeans - coffee.getCoffee());
        this.setCups(cups - 1);
        this.setMoney(money + coffee.getMoney());
    }

    // Getters/Setters
    public int getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(int waterMl) {
        this.waterMl = waterMl;
    }

    public int getMilkMl() {
        return milkMl;
    }

    public void setMilkMl(int milkMl) {
        this.milkMl = milkMl;
    }

    public int getCoffeeBeansG() {
        return coffeeBeansG;
    }

    public void setCoffeeBeansG(int coffeeBeansG) {
        this.coffeeBeansG = coffeeBeansG;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }
}
