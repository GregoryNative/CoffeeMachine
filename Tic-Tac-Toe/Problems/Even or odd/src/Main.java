import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = getScanner();

        ArrayList<Integer> numbers = readArrayFromScanner(scanner);

        printIfOdd(numbers);
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    private static ArrayList<Integer> readArrayFromScanner(Scanner scanner) {
        ArrayList<Integer> numbers = new ArrayList<>();

        while (true) {
            int number = scanner.nextInt();

            if (number == 0) {
                break;
            }

            numbers.add(number);
        }

        return numbers;
    }

    private static void printIfOdd(ArrayList<Integer> numbers) {
        for (int number: numbers) {
            boolean isOdd = number % 2 != 0;
            System.out.println(isOdd ? "odd" : "even");
        }
    }
}