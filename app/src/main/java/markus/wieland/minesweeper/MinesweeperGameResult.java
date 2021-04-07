package markus.wieland.minesweeper;

import markus.wieland.games.game.GameResult;

public class MinesweeperGameResult implements GameResult {

    private final long seconds;
    private final boolean win;

    public MinesweeperGameResult(long seconds, boolean win) {
        this.seconds = seconds;
        this.win = win;
    }

    public long getSeconds() {
        return seconds;
    }

    public boolean isWin() {
        return win;
    }
}
