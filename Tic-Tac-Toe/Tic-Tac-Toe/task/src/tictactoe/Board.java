package tictactoe;

public class Board {
    private int size;

    public Board(int size) {
        this.size = size;
    }

    public void print(State state) {
        for (int i = 0; i < size; i++) {
            printLine(i, state);
        }
    }

    void printLine(int line, State state) {
        for (int i = 0; i < size; i++) {
            System.out.print(state.getValueString(line, i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
