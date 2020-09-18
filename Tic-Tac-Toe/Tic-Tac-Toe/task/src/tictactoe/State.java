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
    private short[][] current;

    public State(int size, String initial) {
        this.size = size;
        this.current = new short[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char value = initial.charAt(i * size + j);
                int valueToInt = value == 'O' ? 1 : value == 'X' ? 2 : 0;
                this.current[i][j] = (short) valueToInt;
            }
        }
    }

    public short[][] getState() {
        return this.current;
    }

    public short getValue(int i, int j) {
        return this.current[i][j];
    }

    public void setValueTo(int value, int i, int j) throws OccupiedException {
        if (this.current[i][j] == 1 || this.current[i][j] == 2) {
            throw new OccupiedException();
        }

        this.current[i][j] = (short) value;
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
        boolean NOT_FINISHED = false;
        int N = this.size;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                short value = this.current[i][j];

                if (value == 1) {
                    countO++;
                } else if (value == 2) {
                    countX++;
                } else {
                    NOT_FINISHED = true;
                }
            }
        }

        if (countO > countX + 1 || countX > countO + 1) {
            return Status.IMPOSSIBLE;
        }

        boolean rowX = false;
        boolean rowO = false;

        for (int i = 0; i < winConditions.length; i++) {
            int[] condition = winConditions[i];

            short first = getValueByIndex(condition[0]);
            short second = getValueByIndex(condition[1]);
            short third = getValueByIndex(condition[2]);

            if (first == second && second == third) {
                if (first == 2) {
                    rowX = true;
                }

                if (first == 1) {
                    rowO = true;
                }
            }
        }

        if (rowO && rowX) {
            return Status.IMPOSSIBLE;
        }

        if (rowX) {
            return Status.X_WINS;
        }

        if (rowO) {
            return Status.O_WINS;
        }

        if (NOT_FINISHED) {
            return Status.NOT_FINISHED;
        }

        return Status.DRAW;
    }

    public short getValueByIndex(int index) {
        int i = Math.round(index / size);
        int j = index % size;

        return this.getValue(i, j);
    }
}
