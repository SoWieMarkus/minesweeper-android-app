package markus.wieland.minesweeper;

import android.os.CountDownTimer;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.game.Game;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.minesweeper.persistence.MinesweeperGameState;
import markus.wieland.minesweeper.view.MinesweeperCellView;

public class Minesweeper extends Game<MinesweeperGameState, MinesweeperGameResult> implements MinesweeperGameBoardFieldInteractionListener {

    private final MinesweeperGameBoard minesweeperGameBoard;
    private long seconds;
    private final Difficulty difficulty;
    private final CountDownTimer timer;

    public Minesweeper(MinesweeperGameState minesweeperGameState, MinesweeperGameBoard minesweeperGameBoard, GameEventListener<MinesweeperGameResult> gameEventListener) {
        super(gameEventListener);
        this.minesweeperGameBoard = minesweeperGameBoard;
        minesweeperGameBoard.loadGameState(minesweeperGameState);
        this.seconds = minesweeperGameState.getSeconds();
        minesweeperGameBoard.updateTime(seconds);
        minesweeperGameBoard.updateRemainingBombs();
        this.difficulty = minesweeperGameState.getDifficulty();
        minesweeperGameBoard.setGameBoardInteractionListener(this);
        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds++;
                minesweeperGameBoard.updateTime(seconds);
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
    }

    @Override
    public void start() {
        super.start();
        timer.start();
    }

    @Override
    public void finish(MinesweeperGameResult gameResult) {
        super.finish(gameResult);
        timer.cancel();
    }

    @Override
    public MinesweeperGameState getGameState() {
        return new MinesweeperGameState(seconds, difficulty, minesweeperGameBoard.getGameState());
    }

    @Override
    public MinesweeperGameResult getResult() {
        if (minesweeperGameBoard.checkForLose()) return new MinesweeperGameResult(seconds, false);
        else if (minesweeperGameBoard.checkForWin()) return new MinesweeperGameResult(seconds, true);
        return null;
    }

    @Override
    public void onClick(MinesweeperCellView minesweeperCellView) {
        if (minesweeperCellView.isMarkedAsSave() ||minesweeperCellView.isUncovered()) return;
        minesweeperGameBoard.uncover(minesweeperCellView.getCoordinate());
        MinesweeperGameResult minesweeperGameResult = getResult();
        if (minesweeperGameResult != null) {
            minesweeperGameBoard.revealTheBoard();
            finish(minesweeperGameResult);
        }
    }

    @Override
    public void onLongClick(MinesweeperCellView minesweeperCellView) {
        if (minesweeperCellView.isUncovered()) return;
        minesweeperCellView.toggleMarkedAsSaved();
        minesweeperGameBoard.updateRemainingBombs();
    }

    @Override
    public void onAbort() {

    }
}
