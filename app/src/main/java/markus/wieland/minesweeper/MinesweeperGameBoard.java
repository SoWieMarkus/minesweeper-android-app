package markus.wieland.minesweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.Matrix;
import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.GameResult;
import markus.wieland.games.game.grid.GridGameBoardView;
import markus.wieland.games.persistence.GameState;
import markus.wieland.minesweeper.persistence.MinesweeperGameState;
import markus.wieland.minesweeper.persistence.MinesweeperGameStateField;
import markus.wieland.minesweeper.view.MinesweeperCellView;
import markus.wieland.minesweeper.view.MinesweeperGridAdapter;

public class MinesweeperGameBoard extends GridGameBoardView<MinesweeperCellView> {

    private GridView minesweeperGridView;

    public MinesweeperGameBoard(@NonNull Context context) {
        super(context);
    }

    public MinesweeperGameBoard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MinesweeperGameBoard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initializeFields() {
        minesweeperGridView = findViewById(R.id.grid);
    }

    @Override
    protected void load() {
        // Wo don't want to load the default procedure here so we have to override the method
    }

    @Override
    protected int getSizeX() {
        return matrix.getSizeX();
    }

    @Override
    protected int getSizeY() {
        return matrix.getSizeY();
    }

    @Override
    protected MinesweeperGameResult checkGameForFinish() {
        return null;
    }

    public boolean checkForWin(){
        for (MinesweeperCellView view : matrix) {
            if (!view.isUncovered() && !view.isBomb()) return false;
        }
        return true;
    }

    public boolean checkForLose(){
        for (MinesweeperCellView view : matrix) {
            if (view.isUncovered() && view.isBomb()) return true;
        }
        return false;
    }

    public void revealTheBoard() {
        for (MinesweeperCellView view : matrix) {
            if (!view.isUncovered()) view.reveal();
        }
    }

    public void uncover(Coordinate coordinate) {
        uncover(coordinate.getX(), coordinate.getY());
    }

    public void uncover(int x, int y) {
        if (x < 0 || y < 0 || x > matrix.getSizeX() - 1 || y > matrix.getSizeY() - 1) return;
        MinesweeperCellView field = matrix.get(x, y);
        if (field.isUncovered()) return;
        field.uncover();
        if (field.isEmpty()) {
            uncover(x - 1, y - 1);
            uncover(x - 1, y);
            uncover(x - 1, y + 1);

            uncover(x, y - 1);
            uncover(x, y + 1);

            uncover(x + 1, y - 1);
            uncover(x + 1, y);
            uncover(x + 1, y + 1);
        }
    }

    public SerializableMatrix<MinesweeperGameStateField> getGameState() {
        SerializableMatrix<MinesweeperGameStateField> minesweeperGameStateFields = new SerializableMatrix<>(matrix.getSizeX(), matrix.getSizeY());
        for (MinesweeperCellView minesweeperCellView : matrix) {
            MinesweeperGameStateField minesweeperGameStateField = new MinesweeperGameStateField(minesweeperCellView.getCoordinate(),
                    minesweeperCellView.isUncovered(), minesweeperCellView.isMarkedAsSave(), minesweeperCellView.getValue());
            minesweeperGameStateFields.set(minesweeperCellView.getCoordinate(), minesweeperGameStateField);
        }
        return minesweeperGameStateFields;
    }


    @Override
    protected void loadGameState(GameState gameState) {
        MinesweeperGameState minesweeperGameState = (MinesweeperGameState) gameState;
        matrix = new Matrix<>(minesweeperGameState.getSizeX(), minesweeperGameState.getSizeY());
        for (MinesweeperGameStateField field : minesweeperGameState) {
            MinesweeperCellView minesweeperCellView = new MinesweeperCellView(getContext(), field.getCoordinate());
            minesweeperCellView.load(field);
            minesweeperCellView.setOnClickListener(view -> ((MinesweeperGameBoardFieldInteractionListener) gameBoardInteractionListener).onClick(minesweeperCellView));
            minesweeperCellView.setOnLongClickListener(view -> {
                ((MinesweeperGameBoardFieldInteractionListener) gameBoardInteractionListener).onLongClick(minesweeperCellView);
                return true;
            });
            minesweeperCellView.invalidate();
            matrix.set(field.getCoordinate().getX(), field.getCoordinate().getY(), minesweeperCellView);
        }
        minesweeperGridView.setNumColumns(minesweeperGameState.getMatrix().getSizeX());
        minesweeperGridView.setAdapter(new MinesweeperGridAdapter(matrix));
    }
}
