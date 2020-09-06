package machine.state;

import machine.Coffee;
import machine.CoffeeFactory;
import machine.CoffeeMachineEngine;

public class ChooseActionState extends CoffeeMachineState {
    public ChooseActionState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        String welcomeMessage = String.format(
            "Write action (%s, %s, %s, %s, %s):",
            CoffeeMachineEngine.Action.BUY,
            CoffeeMachineEngine.Action.FILL,
            CoffeeMachineEngine.Action.TAKE,
            CoffeeMachineEngine.Action.REMAINING,
            CoffeeMachineEngine.Action.EXIT
        );
        this.engine.getPrinter().print(welcomeMessage);
    }

    @Override
    public void exec(String command) {
        this.chooseAction(command);
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.CHOOSE_ACTION;
    }

    public void chooseAction(String action) {
        switch (CoffeeMachineEngine.Action.valueOf(action.toUpperCase())) {
            case BUY:
                this.engine.setState(new ChooseCoffeeState(this.engine));
                break;
            case FILL:
                this.engine.setState(new FillWaterState(this.engine));
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

    private void takeAction() {
        int currentMoney = this.engine.getMoney();

        this.engine.getPrinter().print("I gave you $" + currentMoney);
        this.engine.getPrinter().divider();

        this.engine.setMoney(0);
    }

    private void remainingAction() {
        this.printInfo();
    }

    public void exitAction() {
        this.engine.setState(new ExitState(this.engine));
    }

    private void printInfo() {
        this.engine.getPrinter().divider();
        this.engine.getPrinter().print("The coffee machine has:");
        this.engine.getPrinter().print(String.valueOf(this.engine.getWaterMl()) + " of water");
        this.engine.getPrinter().print(String.valueOf(this.engine.getMilkMl()) + " of milk");
        this.engine.getPrinter().print(String.valueOf(this.engine.getCoffeeBeansG()) + " of coffee beans");
        this.engine.getPrinter().print(String.valueOf(this.engine.getCups()) + " of disposable cups");
        this.engine.getPrinter().print("$" + String.valueOf(this.engine.getMoney()) + " of money");
        this.engine.getPrinter().divider();
    }
}
