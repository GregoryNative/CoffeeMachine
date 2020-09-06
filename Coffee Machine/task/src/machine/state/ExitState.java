package machine.state;

import machine.CoffeeMachineEngine;

public class ExitState extends CoffeeMachineState {
    public ExitState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {}

    @Override
    public void exec(String command) {}

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.EXIT;
    }
}
