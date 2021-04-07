package markus.wieland.minesweeper.persistence;

import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.grid.GridGameState;

public class MinesweeperGameState extends GridGameState<MinesweeperGameStateField> {

    private final Difficulty difficulty;
    private final long seconds;

    private final int sizeX;
    private final int sizeY;

    public MinesweeperGameState(SerializableMatrix<MinesweeperGameStateField> matrix, Difficulty difficulty, int sizeX, int sizeY) {
        super(matrix);
        this.difficulty = difficulty;
        this.seconds = 0;
        this.sizeY = sizeY;
        this.sizeX = sizeX;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public MinesweeperGameState(long seconds, Difficulty difficulty, SerializableMatrix<MinesweeperGameStateField> matrix) {
        super(matrix);
        this.seconds = seconds;
        this.difficulty = difficulty;
        this.sizeX = matrix.getSizeX();
        this.sizeY = matrix.getSizeY();
    }

    public long getSeconds() {
        return seconds;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
