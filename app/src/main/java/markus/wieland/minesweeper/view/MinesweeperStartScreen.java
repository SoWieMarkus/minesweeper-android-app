package markus.wieland.minesweeper.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.minesweeper.MainActivity;
import markus.wieland.minesweeper.R;
import markus.wieland.minesweeper.configuration.MinesweeperConfiguration;
import markus.wieland.minesweeper.persistence.MinesweeperConfigurationCookie;


public class MinesweeperStartScreen extends StartScreenView implements SeekBar.OnSeekBarChangeListener {

    private Difficulty difficulty;

    private SeekBar seekBarSizeX;
    private TextView textViewSizeX;

    private static final int MAX_SIZE_X = 20;
    private static final int MIN_SIZE_X = 7;

    public static final int DEFAULT_SIZE_X = 10;

    private MinesweeperConfigurationCookie minesweeperConfigurationCookie;

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
        return new MinesweeperConfiguration(difficulty, seekBarSizeX.getProgress(), calculatePerfectSetup());
    }

    @Override
    protected void onBuild() {

        minesweeperConfigurationCookie = new MinesweeperConfigurationCookie(getContext());

        textViewSizeX = findViewById(R.id.minesweeper_start_screen_size_x_value);

        seekBarSizeX = findViewById(R.id.minesweeper_start_screen_size_x);
        seekBarSizeX.setOnSeekBarChangeListener(this);
        seekBarSizeX.setMin(MIN_SIZE_X);
        seekBarSizeX.setMax(MAX_SIZE_X);
        seekBarSizeX.setProgress(minesweeperConfigurationCookie.getSizeX() > MAX_SIZE_X
                ? DEFAULT_SIZE_X
                : minesweeperConfigurationCookie.getSizeX());

        findViewById(R.id.minesweeper_start_screen_easy).setOnClickListener(view -> loadWithDifficulty(Difficulty.EASY));
        findViewById(R.id.minesweeper_start_screen_medium).setOnClickListener(view -> loadWithDifficulty(Difficulty.MEDIUM));
        findViewById(R.id.minesweeper_start_screen_hard).setOnClickListener(view -> loadWithDifficulty(Difficulty.HARD));
    }

    private void loadWithDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        minesweeperConfigurationCookie.save(seekBarSizeX.getProgress());
        close();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seekBar.setProgress(i);
        textViewSizeX.setText(String.valueOf(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No use for us
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No use for us
    }

    @Override
    protected void onShow() {
        super.onShow();
        setBackgroundColor(getContext().getColor(R.color.start));
    }

    private int calculatePerfectSetup() {
        int width = ((MainActivity) getContext()).getWidth();
        int height = ((MainActivity) getContext()).getHeight();
        int widthPerTile = width / seekBarSizeX.getProgress();
        return height / widthPerTile;
    }
}
