package machine;

import machine.state.*;

public class CoffeeMachineEngine extends CoffeeMachineSupplies {
    public enum State {
        CHOOSE_ACTION,
        CHOOSE_COFFEE,
        FILL_WATER,
        FILL_MILK,
        FILL_COFFEE_BEANS,
        FILL_CUPS,
        EXIT;
    }

    public enum Action {
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

    private CoffeeMachineState state = new ChooseActionState(this);

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
        this.getState().info();
    }

    public void exec(String command) {
        this.getState().exec(command);
    }

    // Getters/Setters
    public CoffeeMachineState getState() {
        return this.state;
    }

    public void setState(CoffeeMachineState state) {
        this.state = state;
    }

    public CoffeeMachineEngine.State getCurrentStateName() {
        return this.getState().getStateName();
    }

    public CoffeeMachinePrinter getPrinter() {
        return this.printer;
    }

    public CoffeeMachineCalculator getCalculator() {
        return this.calculator;
    }
}
