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
        this.analyze();
    }

    public void analyze() {
        State.Status status = this.state.getStateStatus();

        switch (status) {
            case NOT_FINISHED:
                print("Game not finished");
                break;
            case DRAW:
                print("Draw");
                break;
            case X_WINS:
                print("X wins");
                break;
            case O_WINS:
                print("O wins");
                break;
            case IMPOSSIBLE:
                print("Impossible");
                break;
        }
    }

    private void print(String str) {
        System.out.println(str);
    }
}
