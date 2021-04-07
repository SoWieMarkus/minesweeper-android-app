package markus.wieland.minesweeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.view.EndScreenView;
import markus.wieland.minesweeper.R;

public class MinesweeperEndScreen extends EndScreenView {

    private Button playAgain;

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

    }

    @Override
    protected void onBuild() {
        findViewById(R.id.playAgain).setOnClickListener(view -> close());
    }
}
