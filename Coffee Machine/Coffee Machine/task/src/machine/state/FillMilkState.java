package machine.state;

import machine.CoffeeMachineEngine;

public class FillMilkState extends CoffeeMachineState {
    public FillMilkState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        this.engine.getPrinter().print("Write how many ml of milk do you want to add:");
    }

    @Override
    public void exec(String command) {
        int milk = Integer.parseInt(command);
        this.engine.setMilkMl(this.engine.getMilkMl() + milk);

        this.engine.setState(new FillCoffeeBeansState(this.engine));
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.FILL_MILK;
    }
}
