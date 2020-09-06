package machine.state;

import machine.CoffeeMachineEngine;

public abstract class CoffeeMachineState {
    protected CoffeeMachineEngine engine;

    CoffeeMachineState(CoffeeMachineEngine engine) {
        this.engine = engine;
    }

    public abstract void info();
    public abstract void exec(String command);
    public abstract CoffeeMachineEngine.State getStateName();
}
