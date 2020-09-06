package machine;

public class CoffeeMachinePrinter {
    private String name;

    public CoffeeMachinePrinter(String name) {
        this.name = name;
    }

    public void print(String str) {
        // String message = name + ": " + str;
        String message = str;

        System.out.println(message);
    }

    public void divider() {
        System.out.println();
    }
}
