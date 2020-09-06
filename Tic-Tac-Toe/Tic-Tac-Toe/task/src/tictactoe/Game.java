package tictactoe;

public class Game {
    private final int boardSize = 3;
    private final short[] initialState = {
        2, 1, 2,
        1, 2, 1,
        2, 2, 1
    };

    private Board board = new Board(boardSize);
    private State state = new State(boardSize, initialState);

    public void start() {
        this.board.print(state);
    }
}
