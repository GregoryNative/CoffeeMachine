package machine;

public class CoffeeFactory {
    public static Coffee create(String type) {
        Coffee coffee;

        switch(type) {
            case "espresso":
                coffee = new Coffee(250, 0, 16, 4);
                break;
            case "latte":
                coffee = new Coffee(350, 75, 20, 7);
                break;
            case "cappuccino":
                coffee = new Coffee(200, 100, 12, 6);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        
        return coffee;
    }
    
    public static Coffee create(int type) {
        String typeName;

        switch (type) {
            case 1:
                typeName = "espresso";
                break;
            case 2:
                typeName = "latte";
                break;
            case 3:
                typeName = "cappuccino";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        
        return CoffeeFactory.create(typeName);
    }
}
