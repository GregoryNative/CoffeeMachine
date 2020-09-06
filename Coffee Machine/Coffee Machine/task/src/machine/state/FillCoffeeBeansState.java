package machine.state;

import machine.CoffeeMachineEngine;

public class FillCoffeeBeansState extends CoffeeMachineState {
    public FillCoffeeBeansState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        this.engine.getPrinter().print("Write how many grams of coffee beans do you want to add:");
    }

    @Override
    public void exec(String command) {
        int coffee = Integer.parseInt(command);
        this.engine.setCoffeeBeansG(this.engine.getCoffeeBeansG() + coffee);

        this.engine.setState(new FillCupsState(this.engine));
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.FILL_COFFEE_BEANS;
    }
}
