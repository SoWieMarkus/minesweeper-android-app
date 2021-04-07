package markus.wieland.minesweeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.game.Difficulty;
import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.minesweeper.R;
import markus.wieland.minesweeper.configuration.MinesweeperConfiguration;
import markus.wieland.minesweeper.persistence.MinesweeperConfigurationCookie;


public class MinesweeperStartScreen extends StartScreenView implements SeekBar.OnSeekBarChangeListener {

    private Difficulty difficulty;

    private SeekBar seekBarSizeX;
    private SeekBar seekBarSizeY;
    private TextView textViewSizeX;
    private TextView textViewSizeY;

    private static final int MAX_SIZE_X = 20;
    private static final int MIN_SIZE_X = 7;

    private static final int MAX_SIZE_Y = 25;
    private static final int MIN_SIZE_Y = 7;

    private static final String SIZE_X_SEEK_BAR = "sizeX";
    private static final String SIZE_Y_SEEK_BAR = "sizeY";

    public static final int DEFAULT_SIZE_X = 10;
    public static final int DEFAULT_SIZE_Y = 15;

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
        return new MinesweeperConfiguration(difficulty, seekBarSizeX.getProgress(), seekBarSizeY.getProgress());
    }

    @Override
    protected void onBuild() {

        minesweeperConfigurationCookie = new MinesweeperConfigurationCookie(getContext());

        textViewSizeX = findViewById(R.id.minesweeper_start_screen_size_x_value);
        textViewSizeY = findViewById(R.id.minesweeper_start_screen_size_y_value);

        seekBarSizeX = findViewById(R.id.minesweeper_start_screen_size_x);
        seekBarSizeX.setTag(SIZE_X_SEEK_BAR);
        seekBarSizeX.setOnSeekBarChangeListener(this);
        seekBarSizeX.setMin(MIN_SIZE_X);
        seekBarSizeX.setMax(MAX_SIZE_X);
        seekBarSizeX.setProgress(minesweeperConfigurationCookie.getSizeX() > MAX_SIZE_X
                ? DEFAULT_SIZE_X
                : minesweeperConfigurationCookie.getSizeX());

        seekBarSizeY = findViewById(R.id.minesweeper_start_screen_size_y);
        seekBarSizeY.setOnSeekBarChangeListener(this);
        seekBarSizeY.setTag(SIZE_Y_SEEK_BAR);
        seekBarSizeY.setMin(MIN_SIZE_Y);
        seekBarSizeY.setMax(MAX_SIZE_Y);

        seekBarSizeY.setProgress(minesweeperConfigurationCookie.getSizeY() > MAX_SIZE_Y
                ? DEFAULT_SIZE_Y
                : minesweeperConfigurationCookie.getSizeY());

        findViewById(R.id.minesweeper_start_screen_easy).setOnClickListener(view -> loadWithDifficulty(Difficulty.EASY));
        findViewById(R.id.minesweeper_start_screen_medium).setOnClickListener(view -> loadWithDifficulty(Difficulty.MEDIUM));
        findViewById(R.id.minesweeper_start_screen_hard).setOnClickListener(view -> loadWithDifficulty(Difficulty.HARD));
    }

    private void loadWithDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        minesweeperConfigurationCookie.save(seekBarSizeX.getProgress(), seekBarSizeY.getProgress());
        close();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seekBar.setProgress(i);
        if (seekBar.getTag().equals(SIZE_X_SEEK_BAR)) textViewSizeX.setText(String.valueOf(i));
        else textViewSizeY.setText(String.valueOf(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No use for us
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No use for us
    }
}
