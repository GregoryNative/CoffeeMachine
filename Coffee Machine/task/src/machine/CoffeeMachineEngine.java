package machine;

public class CoffeeMachineEngine extends CoffeeMachineSupplies {
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

    private State state = State.CHOOSE_ACTION;

    public CoffeeMachineEngine(int waterMl, int milkMl, int coffeeBeansG) {
        super(waterMl, milkMl, coffeeBeansG);
    }

    public CoffeeMachineEngine withCups(int cups) {
        this.setCups(cups);

        return this;
    }

    public CoffeeMachineEngine withMoney(int money) {
        this.setMoney(money);

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

                this.setState(State.FILL_MILK);
                break;
            case FILL_MILK:
                int milk = Integer.parseInt(command);
                this.setMilkMl(this.getMilkMl() + milk);

                this.setState(State.FILL_COFFEE_BEANS);
                break;
            case FILL_COFFEE_BEANS:
                int coffee = Integer.parseInt(command);
                this.setCoffeeBeansG(this.getCoffeeBeansG() + coffee);

                this.setState(State.FILL_CUPS);
                break;
            case FILL_CUPS:
                int cups = Integer.parseInt(command);
                this.setCups(this.getCups() + cups);

                this.setState(State.CHOOSE_ACTION);
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
                this.setState(State.CHOOSE_COFFEE);
                break;
            case FILL:
                this.setState(State.FILL_WATER);
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

    private void buyAction(String coffeeTypeStr) {
        if (coffeeTypeStr.equals("back")) {
            this.setState(State.CHOOSE_ACTION);
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

        this.setState(State.CHOOSE_ACTION);
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
        this.setState(State.EXIT);
    }

    private void printInfo() {
        this.printer.divider();
        this.printer.print("The coffee machine has:");
        this.printer.print(String.valueOf(this.getWaterMl()) + " of water");
        this.printer.print(String.valueOf(this.getMilkMl()) + " of milk");
        this.printer.print(String.valueOf(this.getCoffeeBeansG()) + " of coffee beans");
        this.printer.print(String.valueOf(this.getCups()) + " of disposable cups");
        this.printer.print("$" + String.valueOf(this.getMoney()) + " of money");
        this.printer.divider();
    }

    public void makeCoffeeAuto(Coffee coffee) {
        int water = this.getWaterMl();
        int milk = this.getMilkMl();
        int coffeeBeans = this.getCoffeeBeansG();
        int cups = this.getCups();
        int money = this.getMoney();

        this.setWaterMl(water - coffee.getWater());
        this.setMilkMl(milk - coffee.getMilk());
        this.setCoffeeBeansG(coffeeBeans - coffee.getCoffee());
        this.setCups(cups - 1);
        this.setMoney(money + coffee.getMoney());
    }

    // Getters/Setters
    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
