import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();

        solver.start();
    }
}

class Solver {
    public void start() {
        Scanner scanner = getScanner();

        ArrayList<Integer> numbers = askNumbers(scanner);

        int summary = sum(numbers);

        print(summary);
    }

    public ArrayList<Integer> askNumbers(Scanner scan) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int currentNumber = askNumber(scan);

        while(currentNumber != 0) {
            result.add(currentNumber);

            currentNumber = askNumber(scan);
        }

        return result;
    }

    public int sum(ArrayList<Integer> numbers) {
        int result = 0;

        for (Integer number : numbers) {
            result += number;
        }

        return result;
    }

    public int askNumber(Scanner scan) {
        return scan.nextInt();
    }

    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    public void print(int number) {
        System.out.println(String.valueOf(number));
    }
}