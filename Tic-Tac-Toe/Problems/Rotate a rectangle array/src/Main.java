import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        int[][] arr = readArray(n, m, scanner);
        
        int[][] rotatedArr = rotateArr(arr);
        
        print(rotatedArr);
    }
    
    public static int[][] readArray(int n, int m, Scanner scanner) {
        int[][] result = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = scanner.nextInt();
            }
        }
        
        return result;
    }

    public static int[][] rotateArr(int[][] arr) {
        int row = arr[0].length;
        int column = arr.length;
        
        int[][] result = new int[row][column];

        for (int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                result[j][column - i - 1] = arr[i][j];
            }
        }

        return result;
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
