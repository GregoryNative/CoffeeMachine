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

    public State(int size, String initial) {
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
}
