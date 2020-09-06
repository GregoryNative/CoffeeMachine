package machine;

public class Coffee {
    private int water;
    private int milk;
    private int coffee;
    private int money;

    public Coffee(int water, int milk, int coffee, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.money = money;
    }

    public int getWater() {
        return water;
    }
    public int getMilk() {
        return milk;
    }
    public int getCoffee() {
        return coffee;
    }
    public int getMoney() {
        return money;
    }
}
