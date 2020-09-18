package tictactoe;

import java.util.Map;
import java.util.Scanner;

public class Game {
    private final int boardSize = 3;

    private Board board = new Board(boardSize);
    private State state;
    private Scanner scanner;

    Game(String initialState, Scanner scanner) {
        this.state = new State(boardSize, initialState);
        this.scanner = scanner;
    }

    public void start() {
        this.board.print(state);

        this.askCoordinates();
    }

    private void askCoordinates() {
        Main.print("Enter the coordinates: ");

        try {
            int x = Integer.parseInt(Main.askString(scanner));
            int y = Integer.parseInt(Main.askString(scanner));

            this.setX(x, y);
        } catch (NumberFormatException exception) {
            Main.println("You should enter numbers!");
            this.askCoordinates();
        } catch (OccupiedException exception) {
            Main.println("This cell is occupied! Choose another one!");
            this.askCoordinates();
        } catch (CoordinateValueException exception) {
            Main.println("Coordinates should be from 1 to 3!");
            this.askCoordinates();
        }
    }

    public static final Map<String, int[]> TableOfIndexes = Map.of(
        "11", new int[]{2, 0},
        "12", new int[]{1, 0},
        "13", new int[]{0, 0},
        "21", new int[]{2, 1},
        "22", new int[]{1, 1},
        "23", new int[]{0, 1},
        "31", new int[]{2, 2},
        "32", new int[]{1, 2},
        "33", new int[]{0, 2}
    );

    public void setX(int x, int y) throws OccupiedException, CoordinateValueException {
        if (x > boardSize || y > boardSize) {
            throw new CoordinateValueException();
        }

        int[] coordinates = TableOfIndexes.get("" + x + y);

        this.state.setValueTo(2, coordinates[0], coordinates[1]);
        this.board.print(state);
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
