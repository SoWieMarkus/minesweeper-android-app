package markus.wieland.minesweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.GameBoardField;
import markus.wieland.games.game.grid.GridGameBoardFieldView;
import markus.wieland.games.game.view.GameStateField;
import markus.wieland.minesweeper.R;
import markus.wieland.minesweeper.persistence.MinesweeperGameStateField;

public class MinesweeperCellView extends View implements GridGameBoardFieldView {

    private int value;

    private boolean isUncovered;
    private boolean isMarkedAsSave;
    private boolean wasRevealed;

    private final Coordinate coordinate;

    public static final int BOMB = -10;

    public MinesweeperCellView(Context context, Coordinate coordinate) {
        super(context);
        this.coordinate = coordinate;
        this.wasRevealed = false;
    }

    public boolean isBomb() {
        return value == BOMB;
    }

    @Override
    public void load(GameStateField stateField) {
        MinesweeperGameStateField minesweeperGameStateField = (MinesweeperGameStateField) stateField;
        this.isMarkedAsSave = minesweeperGameStateField.isMarkedAsSafe();
        this.value = minesweeperGameStateField.getValue();
        this.isUncovered = minesweeperGameStateField.isUncovered();
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getValue() {
        return value;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public boolean isMarkedAsSave() {
        return isMarkedAsSave;
    }

    public void uncover() {
        this.isUncovered = true;
        invalidate();
    }

    public void reveal() {
        this.isUncovered = true;
        this.wasRevealed = true;
        invalidate();
    }

    public void toggleMarkedAsSaved() {
        this.isMarkedAsSave = !isMarkedAsSave;
        invalidate();
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isMarkedAsSave) {
            drawFlag(canvas);
            return;
        }

        if (wasRevealed && isBomb()) {
            drawNormalBomb(canvas);
            return;
        }

        if (isUncovered && isBomb()) {
            drawBomb(canvas);
            return;
        }

        if (isUncovered) {
            drawNumber(canvas, value);
            return;
        }

        drawButton(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void drawNumber(Canvas canvas, int number) {
        draw("number_" + number, canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        draw(R.drawable.bomb_normal, canvas);
    }

    private void drawBomb(Canvas canvas) {
        draw(R.drawable.bomb_exploded, canvas);
    }

    private void drawButton(Canvas canvas) {
        draw(R.drawable.button, canvas);
    }

    private void drawFlag(Canvas canvas) {
        draw(R.drawable.flag, canvas);
    }

    private void draw(@DrawableRes int drawableRes, Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void draw(String drawableResString, Canvas canvas) {
        draw(getContext().getResources().getIdentifier(drawableResString, "drawable", getContext().getPackageName()), canvas);
    }
}
