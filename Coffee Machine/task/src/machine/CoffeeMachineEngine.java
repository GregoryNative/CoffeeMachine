package machine;

import java.util.EnumMap;

public class CoffeeMachineEngine {
    public static enum State {
        CHOOSE_ACTION,
        CHOOSE_COFFEE,
        FILL_WATER,
        FILL_MILK,
        FILL_COFFEE_BEANS,
        FILL_CUPS,
        EXIT;
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

    private State state = State.CHOOSE_ACTION;

    private boolean isExitStarted = false;

    public static CoffeeMachineEngine create() {
        return new CoffeeMachineEngine();
    }

    public CoffeeMachineEngine() {
        this.waterMl = 0;
        this.milkMl = 0;
        this.coffeeBeansG = 0;
    }

    public CoffeeMachineEngine(int waterMl, int milkMl, int coffeeBeansG) {
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansG = coffeeBeansG;
    }

    public CoffeeMachineEngine withParameters(int waterMl, int milkMl, int coffeeBeansG) {
        return new CoffeeMachineEngine(waterMl, milkMl, coffeeBeansG);
    }


    public CoffeeMachineEngine withCups(int cups) {
        this.cups = cups;

        return this;
    }

    public CoffeeMachineEngine withMoney(int money) {
        this.money = money;

        return this;
    }

    public CoffeeMachineEngine withPrinter(CoffeeMachinePrinter printer) {
        this.printer = printer;

        return this;
    }

    public CoffeeMachineEngine withCalculator(CoffeeMachineCalculator calculator) {
        this.calculator = calculator;

        return this;
    }

    // PRIVATE COFFEE MAKER METHODS
//    private void start() {
//        print("Starting to make a coffee");
//    }
//
//    private void grind() {
//        print("Grinding coffee beans");
//    }
//
//    private void boil() {
//        print("Boiling water");
//    }
//
//    private void mix() {
//        print("Mixing boiled water with crushed coffee beans");
//    }
//
//    private void pourCoffee() {
//        print("Pouring coffee into the cup");
//    }
//
//    private void pourMilk() {
//        print("Pouring some milk into the cup");
//    }
//
//    private void finish() {
//        print("Coffee is ready!");
//    }

    private void print(String str) {
        this.printer.print(str);
    }

//    public void makeCoffee() {
//        this.start();
//        this.grind();
//        this.boil();
//        this.mix();
//        this.pourCoffee();
//        this.pourMilk();
//        this.finish();
//    }

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

    public void info() {
        switch (state) {
            case CHOOSE_ACTION:
                this.welcome();
                break;
            case CHOOSE_COFFEE:
                this.printer.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
                break;
            case FILL_WATER:
                this.printer.print("Write how many ml of water do you want to add:");
                break;
            case FILL_MILK:
                this.printer.print("Write how many ml of milk do you want to add:");
                break;
            case FILL_COFFEE_BEANS:
                this.printer.print("Write how many grams of coffee beans do you want to add:");
                break;
            case FILL_CUPS:
                this.printer.print("Write how many disposable cups of coffee do you want to add:");
                break;
        }
    }

    public void exec(String command) {
        switch (state) {
            case CHOOSE_ACTION:
                this.chooseAction(command);
                break;
            case CHOOSE_COFFEE:
                this.buyAction(command);
                break;
            case FILL_WATER:
                int water = Integer.parseInt(command);
                this.setWaterMl(this.getWaterMl() + water);

                this.state = State.FILL_MILK;
                break;
            case FILL_MILK:
                int milk = Integer.parseInt(command);
                this.setMilkMl(this.getMilkMl() + milk);

                this.state = State.FILL_COFFEE_BEANS;
                break;
            case FILL_COFFEE_BEANS:
                int coffee = Integer.parseInt(command);
                this.setCoffeeBeansG(this.getCoffeeBeansG() + coffee);

                this.state = State.FILL_CUPS;
                break;
            case FILL_CUPS:
                int cups = Integer.parseInt(command);
                this.setCups(this.getCups() + cups);

                this.state = State.CHOOSE_ACTION;
                break;
        }
    }

    public void welcome() {
        String welcomeMessage = String.format(
            "Write action (%s, %s, %s, %s, %s):",
            CoffeeMachineEngine.Action.BUY,
            CoffeeMachineEngine.Action.FILL,
            CoffeeMachineEngine.Action.TAKE,
            CoffeeMachineEngine.Action.REMAINING,
            CoffeeMachineEngine.Action.EXIT
        );
        this.printer.print(welcomeMessage);
    }

    public void chooseAction(String action) {
        switch (CoffeeMachineEngine.Action.valueOf(action.toUpperCase())) {
            case BUY:
                this.state = State.CHOOSE_COFFEE;
                break;
            case FILL:
                this.state = State.FILL_WATER;
                break;
            case TAKE:
                this.takeAction();
                break;
            case REMAINING:
                this.remainingAction();
                break;
            case EXIT:
                this.state = State.EXIT;
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

    private void buyAction(String coffeeTypeStr) {
        if (coffeeTypeStr.equals("back")) {
            this.state = State.CHOOSE_ACTION;
            return;
        }

        int coffeeType = Integer.parseInt(coffeeTypeStr);
        if (coffeeType > 0 && coffeeType < 4) {
            Coffee coffee = CoffeeFactory.create(coffeeType);

            if (this.calculator.calculateIfEnoughResources(this, coffee)) {
                this.printer.print("I have enough resources, making you a coffee!");
                this.makeCoffeeAuto(coffee);
            }
        }

        this.printer.divider();

        this.state = State.CHOOSE_ACTION;
//        this.buyAction();
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
        this.printer.divider();

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
