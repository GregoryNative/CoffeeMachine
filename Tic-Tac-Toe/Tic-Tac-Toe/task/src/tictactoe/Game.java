package tictactoe;

public class Game {
    private final int boardSize = 3;

    private Board board = new Board(boardSize);
    private State state;

    Game(String initialState) {
        this.state = new State(boardSize, initialState);
    }

    public void start() {
        this.board.print(state);
    }
}
