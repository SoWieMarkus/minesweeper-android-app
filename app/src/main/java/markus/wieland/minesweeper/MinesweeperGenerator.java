package markus.wieland.minesweeper;

import java.util.Random;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.minesweeper.configuration.MinesweeperConfiguration;
import markus.wieland.minesweeper.persistence.MinesweeperGameState;
import markus.wieland.minesweeper.persistence.MinesweeperGameStateField;
import markus.wieland.minesweeper.view.MinesweeperCellView;

public class MinesweeperGenerator extends GameGenerator<MinesweeperGameState> {

    private final SerializableMatrix<MinesweeperGameStateField> matrix;
    private final Random random;

    public MinesweeperGenerator(MinesweeperConfiguration configuration) {
        super(configuration);
        this.random = new Random();
        this.matrix = new SerializableMatrix<>(configuration.getSizeX(), configuration.getSizeY());
        build();
    }

    private void build() {
        initializeMatrix();
        placeBombs();
        calculateValueOfOtherFields();
    }

    private void initializeMatrix() {
        for (int x = 0; x < matrix.getSizeX(); x++) {
            for (int y = 0; y < matrix.getSizeY(); y++) {
                matrix.set(x, y, new MinesweeperGameStateField(new Coordinate(x, y)));
            }
        }
    }

    private void placeBombs() {
        int seedingFactor = getSeedingFactor();
        int totalPieces = getTotalPieces();
        for (MinesweeperGameStateField minesweeperGameStateField : matrix) {
            int randomValue = random.nextInt(totalPieces);
            if (randomValue > seedingFactor)
                minesweeperGameStateField.setValue(MinesweeperCellView.BOMB);
        }
    }

    private void calculateValueOfOtherFields() {
        for (int x = 0; x < matrix.getSizeX(); x++) {
            for (int y = 0; y < matrix.getSizeY(); y++) {
                if (!matrix.get(x, y).isBomb()) matrix.get(x, y).setValue(countBombs(x, y));
            }
        }
    }

    private int countBombs(int i, int j) {
        int value = 0;

        value += isBomb(i - 1, j - 1);
        value += isBomb(i - 1, j);
        value += isBomb(i - 1, j + 1);

        value += isBomb(i, j - 1);
        value += isBomb(i, j + 1);

        value += isBomb(i + 1, j - 1);
        value += isBomb(i + 1, j);
        value += isBomb(i + 1, j + 1);

        return value;
    }

    private int isBomb(int i, int j) {
        if (i < 0 || j < 0 || i > getConfiguration().getSizeX()-1 || j > getConfiguration().getSizeY()-1)
            return 0;
        return matrix.get(i, j).isBomb() ? 1 : 0;
    }

    private int getSeedingFactor() {
        int totalPieces = getTotalPieces();
        float factor;
        switch (getConfiguration().getDifficulty()) {
            case EASY:
                factor = 0.9f;
                break;
            case MEDIUM:
                factor = 0.8f;
                break;
            default:
                factor = 0.7f;
                break;
        }
        return (int) (totalPieces * factor);
    }

    private int getTotalPieces() {
        return getConfiguration().getSizeX() * getConfiguration().getSizeY();
    }

    @Override
    public MinesweeperConfiguration getConfiguration() {
        return (MinesweeperConfiguration) configuration;
    }

    @Override
    public MinesweeperGameState generate() {
        return new MinesweeperGameState(matrix,
                getConfiguration().getDifficulty(), getConfiguration().getSizeX(), getConfiguration().getSizeY());
    }
}
