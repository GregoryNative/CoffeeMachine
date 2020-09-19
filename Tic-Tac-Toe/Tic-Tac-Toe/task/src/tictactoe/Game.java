package tictactoe;

import java.util.Map;
import java.util.Scanner;

import tictactoe.State.*;

public class Game {
    private final int boardSize = 3;

    private Board board = new Board(boardSize);
    private State state;
    private Scanner scanner;

    private Seed currentPlayer = Seed.CROSS;
    private Status currentState = Status.NOT_FINISHED;

    Game(String initialState, Scanner scanner) {
        this.state = new State(boardSize, initialState);
        this.scanner = scanner;
    }

    Game(Scanner scanner) {
        this.state = new State(boardSize);
        this.scanner = scanner;
    }

    public void start() {
        this.board.print(state);

        do {
            this.askCoordinates();
            currentState = this.state.getStateStatus();
        } while (currentState == Status.NOT_FINISHED);

        this.analyze();
    }

    private void askCoordinates() {
        this.print("Enter the coordinates: ");

        try {
            int x = Integer.parseInt(this.askString(scanner));
            int y = Integer.parseInt(this.askString(scanner));

            this.setSeedToCoordinate(currentPlayer, x, y);
            currentPlayer = currentPlayer == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS;
        } catch (NumberFormatException exception) {
            this.println("You should enter numbers!");
            this.askCoordinates();
        } catch (OccupiedException exception) {
            this.println("This cell is occupied! Choose another one!");
            this.askCoordinates();
        } catch (CoordinateValueException exception) {
            this.println("Coordinates should be from 1 to 3!");
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

    public void setSeedToCoordinate(Seed seed, int x, int y) throws OccupiedException, CoordinateValueException {
        if (x > boardSize || x < 1 || y > boardSize || y < 1) {
            throw new CoordinateValueException();
        }

        int[] coordinates = TableOfIndexes.get("" + x + y);

        this.state.setValueTo(seed, coordinates[0], coordinates[1]);
        this.board.print(state);
    }

    public void analyze() {
        Status status = this.state.getStateStatus();

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

    private String askString(Scanner scanner) {
        return scanner.next();
    }

    private void print(String str) {
        System.out.print(str);
    }

    private void println(String str) {
        System.out.println(str);
    }
}
