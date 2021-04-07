package markus.wieland.minesweeper.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.minesweeper.R;
import markus.wieland.minesweeper.configuration.MinesweeperConfiguration;


public class MinesweeperStartScreen extends StartScreenView {

    private Difficulty difficulty;

    public MinesweeperStartScreen(@NonNull Context context) {
        super(context);
    }

    public MinesweeperStartScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MinesweeperStartScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected MinesweeperConfiguration getConfiguration() {
        return new MinesweeperConfiguration(difficulty, 15, 20);
    }

    @Override
    protected void onBuild() {
        difficulty = Difficulty.EASY;
        findViewById(R.id.close).setOnClickListener(view -> close());
    }
}
