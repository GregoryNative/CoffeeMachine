package tictactoe;

public class State {
    public enum Status {
        NOT_FINISHED,
        DRAW,
        X_WINS,
        O_WINS,
        IMPOSSIBLE,
    }

    public enum Seed {
        EMPTY,
        CROSS,
        NOUGHT
    }

    private int size;
    private Seed[][] current;

    public State(int size) {
        this.size = size;
        this.current = new Seed[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.current[i][j] = Seed.EMPTY;
            }
        }
    }

    public State(int size, String initial) {
        this.size = size;
        this.current = new Seed[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char value = initial.charAt(i * size + j);
                Seed seedValue = value == 'O' ? Seed.NOUGHT : value == 'X' ? Seed.CROSS : Seed.EMPTY;
                this.current[i][j] = seedValue;
            }
        }
    }

    public Seed getValue(int i, int j) {
        return this.current[i][j];
    }

    public void setValueTo(Seed value, int i, int j) throws OccupiedException {
        if (this.current[i][j] == Seed.NOUGHT || this.current[i][j] == Seed.CROSS) {
            throw new OccupiedException();
        }

        this.current[i][j] = value;
    }

    public String getValueString(int i, int j) {
        Seed value = this.getValue(i, j);

        if (value == Seed.NOUGHT) {
            return "O";
        }

        if (value == Seed.CROSS) {
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
                Seed value = this.current[i][j];

                if (value == Seed.NOUGHT) {
                    countO++;
                } else if (value == Seed.CROSS) {
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

            Seed first = getValueByIndex(condition[0]);
            Seed second = getValueByIndex(condition[1]);
            Seed third = getValueByIndex(condition[2]);

            if (first == second && second == third) {
                if (first == Seed.CROSS) {
                    rowX = true;
                }

                if (first == Seed.NOUGHT) {
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

    public Seed getValueByIndex(int index) {
        int i = Math.round(index / size);
        int j = index % size;

        return this.getValue(i, j);
    }
}
