package markus.wieland.minesweeper;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import markus.wieland.games.GameActivity;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.game.Highscore;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.persistence.GameSaver;
import markus.wieland.games.screen.view.EndScreenView;
import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.minesweeper.configuration.MinesweeperConfiguration;
import markus.wieland.minesweeper.persistence.MinesweeperGameState;

public class MainActivity extends GameActivity<MinesweeperConfiguration, Highscore, MinesweeperGameState, MinesweeperGameResult, Minesweeper> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected StartScreenView initializeStartScreen() {
        return findViewById(R.id.activity_minesweeper_start_screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected EndScreenView initializeEndScreen() {
        return findViewById(R.id.activity_minesweeper_end_screen);
    }

    @Override
    protected GameGenerator<MinesweeperGameState> initializeGenerator(GameConfiguration configuration) {
        return new MinesweeperGenerator((MinesweeperConfiguration) configuration);
    }

    @Override
    protected GameSaver<MinesweeperGameState, Highscore> initializeGameSaver() {
        return new GameSaver<>(MinesweeperGameState.class, Highscore.class, this);
    }

    @Override
    protected void initializeGame(MinesweeperGameState minesweeperGameState) {
        game = new Minesweeper(minesweeperGameState, findViewById(R.id.activity_minesweeper_game_board), this);
        findViewById(R.id.activity_minesweeper_game_board).setVisibility(View.VISIBLE);
        game.start();
    }

    @Override
    protected void onStop() {
        if (gameSaver != null && game != null && game.isRunning())
            gameSaver.save(game.getGameState());
        super.onStop();
    }

    @Override
    public void onGameFinish(MinesweeperGameResult gameResult) {
        super.onGameFinish(gameResult);
        gameSaver.save((MinesweeperGameState) null);
    }

    public int getHeight() {
        return findViewById(R.id.activity_minesweeper_game_board).findViewById(R.id.scrollView).getHeight();
    }

    public int getWidth() {
        return findViewById(R.id.activity_minesweeper_game_board).findViewById(R.id.scrollView).getWidth();
    }
}