package machine.state;

import machine.CoffeeMachineEngine;

public class FillCupsState extends CoffeeMachineState {
    public FillCupsState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        this.engine.getPrinter().print("Write how many disposable cups of coffee do you want to add:");
    }

    @Override
    public void exec(String command) {
        int cups = Integer.parseInt(command);
        this.engine.setCups(this.engine.getCups() + cups);

        this.engine.setState(new ChooseActionState(this.engine));
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.FILL_CUPS;
    }
}
