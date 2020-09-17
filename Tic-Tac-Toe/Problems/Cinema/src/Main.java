import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Solver solver = new Solver(scanner);

        solver.start();
    }
}

class Solver {
    private Scanner scanner;

    public Solver(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        int n = readInt();
        int m = readInt();

        int[][] arr = readArray(n, m);

        int k = readInt();

        int result = findRowWithNAvailableSeats(arr, k);

        System.out.println(result);
    }

    private int findRowWithNAvailableSeats(int[][] arr, int k) {
        int availableSeatsInRow = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    availableSeatsInRow++;
                } else {
                    availableSeatsInRow = 0;
                }

                if (availableSeatsInRow == k) {
                    return i + 1;
                }
            }
            availableSeatsInRow = 0;
        }

        return 0;
    }

    public int readInt() {
        return this.scanner.nextInt();
    }

    public int[][] readArray(int n, int m) {
        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = this.scanner.nextInt();
            }
        }

        return result;
    }
}