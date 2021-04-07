package markus.wieland.minesweeper.configuration;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.GameConfiguration;

public class MinesweeperConfiguration implements GameConfiguration {

    private final Difficulty difficulty;
    private final int sizeX;
    private final int sizeY;

    public MinesweeperConfiguration(Difficulty difficulty, int sizeX, int sizeY) {
        this.difficulty = difficulty;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
