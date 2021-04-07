package markus.wieland.minesweeper;

import markus.wieland.games.GameActivity;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.game.Highscore;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.persistence.GameSaver;
import markus.wieland.games.screen.interact_listener.EndScreenInteractListener;
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
        endScreen.setScreenInteractListener(() -> restartActivity(true, MainActivity.class));
        game = new Minesweeper(minesweeperGameState, findViewById(R.id.activity_minesweeper_game_board), this);
        game.start();
    }

    @Override
    protected void onStop() {
        if (gameSaver != null && game != null)
            gameSaver.save(game.getGameState());
        super.onStop();
    }

    @Override
    public void onGameFinish(MinesweeperGameResult gameResult) {
        super.onGameFinish(gameResult);
        gameSaver.save((MinesweeperGameState) null);
    }
}