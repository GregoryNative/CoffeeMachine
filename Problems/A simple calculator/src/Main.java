import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        calculator.start();
    }
}

class Calculator {
    public void start() {
        Scanner scanner = getScanner();
        String firstNumber = askString(scanner);
        String operation = askString(scanner);
        String secondNumber = askString(scanner);

        long first = Long.parseLong(firstNumber);
        long second = Long.parseLong(secondNumber);

        switch (operation) {
            case "+":
                print(first + second);
                break;
            case "-":
                print(first - second);
                break;
            case "/":
                if (second == 0) {
                    print("Division by 0!");
                    break;
                }

                print(first / second);
                break;
            case "*":
                print(first * second);
                break;
            default:
                print("Unknown operator");
                break;
        }
    }

    public int askNumber(Scanner scan) {
        return scan.nextInt();
    }

    public String askString(Scanner scan) {
        return scan.next();
    }

    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void print(long number) {
        System.out.println(String.valueOf(number));
    }
}