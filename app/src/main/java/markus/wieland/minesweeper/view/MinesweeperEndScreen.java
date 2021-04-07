package markus.wieland.minesweeper.view;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.view.EndScreenView;
import markus.wieland.minesweeper.MinesweeperGameResult;
import markus.wieland.minesweeper.R;

public class MinesweeperEndScreen extends EndScreenView {

    private TextView textViewScore;
    private TextView textViewResult;

    public MinesweeperEndScreen(@NonNull Context context) {
        super(context);
    }

    public MinesweeperEndScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MinesweeperEndScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onNewGameResult() {
        MinesweeperGameResult minesweeperGameResult = (MinesweeperGameResult) gameResult;
        setBackgroundColor(minesweeperGameResult.isWin()
                ? Color.parseColor("#BFFF0000")
                : Color.parseColor("#BF00CC00"));
        textViewScore.setText(DateUtils.formatElapsedTime(minesweeperGameResult.getSeconds()));
        textViewResult.setText(minesweeperGameResult.isWin()
                ? getContext().getString(R.string.minesweeper_win)
                : getContext().getString(R.string.minesweeper_game_over));
    }

    @Override
    protected void onBuild() {

        textViewScore = findViewById(R.id.minesweeper_end_screen_score);
        textViewResult = findViewById(R.id.minesweeper_end_screen_result);

        findViewById(R.id.minesweeper_end_screen_play_again).setOnClickListener(view -> close());
        findViewById(R.id.minesweeper_end_screen_back).setOnClickListener(view -> close());

    }
}
