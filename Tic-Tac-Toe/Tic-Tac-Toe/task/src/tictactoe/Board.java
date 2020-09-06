package tictactoe;

public class Board {
    private int size;

    public Board(int size) {
        this.size = size;
    }

    public void print(State state) {
        printDivider();
        for (int i = 0; i < size; i++) {
            printLine(i, state);
        }
        printDivider();
    }

    void printLine(int line, State state) {
        System.out.print("| ");
        for (int i = 0; i < size; i++) {
            System.out.print(state.getValueString(line, i));
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.println();
    }

    void printDivider() {
        System.out.println("---------");
    }
}
