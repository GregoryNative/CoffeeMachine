package tictactoe;

public class State {
    public enum Status {
        NOT_FINISHED,
        DRAW,
        X_WINS,
        O_WINS,
        IMPOSSIBLE,
    }

    private int size;
    private short[] current;

    public State(int size, short[] initial) {
        this.size = size;
        this.current = new short[size];

        for (int i = 0; i < size; i++) {
            this.current[i] = initial[i];
        }
    }

    public State(int size, String initial) {
        this.current = new short[size];

        for (int i = 0; i < size; i++) {
            char value = initial.charAt(i * size + j);
            int valueToInt = value == 'O' ? 1 : value == 'X' ? 2 : 0;
            this.current[i] = (short) valueToInt;
        }
    }

    public short[] getState() {
        return this.current;
    }

    public short getValue(int i, int j) {
        return this.current[i * size + j];
    }

    public String getValueString(int i, int j) {
        int value = this.getValue(i, j);

        if (value == 1) {
            return "O";
        }

        if (value == 2) {
            return "X";
        }

        return "_";
    }
//
//    public boolean isGameFinished() {
//
//    }
    public static final int[][] winConditions = {
        { 0, 1, 2 },
        { 3, 4, 5 },
        { 6, 7, 8 },
        { 0, 3, 6 },
        { 1, 4, 7 },
        { 2, 5, 8 },
        { 0, 4, 8 },
        { 2, 4, 6 }
    };

    public Status getStateStatus() {
        int countO = 0;
        int countX = 0;
        int N = this.size;

        for (int i = 0; i < N; i++) {
            short value = this.current[i];

            if (value == 1) {
                countO++;
            }

            if (value == 2) {
                countX++;
            }
        }

        if (countO > countX + 1 || countX > countO + 1) {
            return Status.IMPOSSIBLE;
        }

        boolean rowX = false;
        boolean rowO = false;

        for (int i = 0; i < winConditions.length; i++) {

        }



        return Status.DRAW;
    }
}
