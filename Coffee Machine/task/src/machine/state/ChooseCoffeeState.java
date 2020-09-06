package machine.state;

import machine.Coffee;
import machine.CoffeeFactory;
import machine.CoffeeMachineEngine;

public class ChooseCoffeeState extends CoffeeMachineState {
    public ChooseCoffeeState(CoffeeMachineEngine engine) {
        super(engine);
    }

    @Override
    public void info() {
        this.engine.getPrinter().print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
    }

    @Override
    public void exec(String command) {
        this.buyAction(command);
    }

    @Override
    public CoffeeMachineEngine.State getStateName() {
        return CoffeeMachineEngine.State.CHOOSE_COFFEE;
    }

    public void buyAction(String coffeeTypeStr) {
        if (coffeeTypeStr.equals("back")) {
            this.engine.setState(new ChooseActionState(this.engine));
            return;
        }

        int coffeeType = Integer.parseInt(coffeeTypeStr);
        if (coffeeType > 0 && coffeeType < 4) {
            Coffee coffee = CoffeeFactory.create(coffeeType);

            if (this.engine.getCalculator().calculateIfEnoughResources(this.engine, coffee)) {
                this.engine.getPrinter().print("I have enough resources, making you a coffee!");
                this.makeCoffeeAuto(coffee);
            }
        }

        this.engine.getPrinter().divider();

        this.engine.setState(new ChooseActionState(this.engine));
    }

    public void makeCoffeeAuto(Coffee coffee) {
        int water = this.engine.getWaterMl();
        int milk = this.engine.getMilkMl();
        int coffeeBeans = this.engine.getCoffeeBeansG();
        int cups = this.engine.getCups();
        int money = this.engine.getMoney();

        this.engine.setWaterMl(water - coffee.getWater());
        this.engine.setMilkMl(milk - coffee.getMilk());
        this.engine.setCoffeeBeansG(coffeeBeans - coffee.getCoffee());
        this.engine.setCups(cups - 1);
        this.engine.setMoney(money + coffee.getMoney());
    }
}
