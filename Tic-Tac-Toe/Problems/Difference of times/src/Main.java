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

        int first = askAndGetSeconds(scanner);
        int second = askAndGetSeconds(scanner);

        int result = second - first;

        print(result);
    }

    public int askAndGetSeconds(Scanner scanner) {
        int hour = askNumber(scanner);
        int minutes = askNumber(scanner);
        int seconds = askNumber(scanner);

        return (hour * 3600) + (minutes * 60) + seconds;
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