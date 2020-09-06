package machine.state;

import machine.CoffeeMachineEngine;

public class FillWaterState extends CoffeeMachineState {
    public FillWaterState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        this.engine.getPrinter().print("Write how many ml of water do you want to add:");
    }

    @Override
    public void exec(String command) {
        int water = Integer.parseInt(command);
        this.engine.setWaterMl(this.engine.getWaterMl() + water);

        this.engine.setState(new FillMilkState(this.engine));
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.FILL_WATER;
    }
}
