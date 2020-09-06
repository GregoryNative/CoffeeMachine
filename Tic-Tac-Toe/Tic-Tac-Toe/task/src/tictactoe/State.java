package tictactoe;

public class State {
    private short[][] current;

    public State(int size, short[] initial) {
        this.current = new short[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.current[i][j] = initial[i * size + j];
            }
        }
    }

    public short[][] getState() {
        return this.current;
    }

    public short getValue(int i, int j) {
        return this.current[i][j];
    }

    public String getValueString(int i, int j) {
        int value = this.getValue(i, j);

        if (value == 1) {
            return "O";
        }

        if (value == 2) {
            return "X";
        }

        return " ";
    }
}
