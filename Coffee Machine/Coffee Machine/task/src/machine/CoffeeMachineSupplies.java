package machine;

public class CoffeeMachineSupplies {
    private int waterMl;
    private int milkMl;
    private int coffeeBeansG;
    private int cups;
    private int money;

    public CoffeeMachineSupplies(int waterMl, int milkMl, int coffeeBeansG) {
        this.waterMl = waterMl;
        this.milkMl = milkMl;
        this.coffeeBeansG = coffeeBeansG;
    }

    public int getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(int waterMl) {
        this.waterMl = waterMl;
    }

    public int getMilkMl() {
        return milkMl;
    }

    public void setMilkMl(int milkMl) {
        this.milkMl = milkMl;
    }

    public int getCoffeeBeansG() {
        return coffeeBeansG;
    }

    public void setCoffeeBeansG(int coffeeBeansG) {
        this.coffeeBeansG = coffeeBeansG;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }
}
