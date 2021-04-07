package markus.wieland.minesweeper.persistence;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.grid.GridGameStateField;
import markus.wieland.minesweeper.view.MinesweeperCellView;

public class MinesweeperGameStateField extends GridGameStateField {

    private boolean isUncovered;
    private boolean isMarkedAsSafe;
    private int value;

    public MinesweeperGameStateField(Coordinate coordinate, boolean isUncovered, boolean isMarkedAsSafe, int value) {
        super(coordinate);
        this.isUncovered = isUncovered;
        this.isMarkedAsSafe = isMarkedAsSafe;
        this.value = value;
    }

    public MinesweeperGameStateField(Coordinate coordinate) {
        this(coordinate, false, false, 0);
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public boolean isMarkedAsSafe() {
        return isMarkedAsSafe;
    }

    public int getValue() {
        return value;
    }

    public boolean isBomb() {
        return MinesweeperCellView.BOMB == value;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public void setMarkedAsSafe(boolean markedAsSafe) {
        isMarkedAsSafe = markedAsSafe;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
