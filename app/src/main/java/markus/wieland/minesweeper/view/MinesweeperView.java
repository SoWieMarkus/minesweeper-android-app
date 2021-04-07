package markus.wieland.minesweeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MinesweeperView extends GridView {

    public MinesweeperView(Context context) {
        super(context);
    }

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MinesweeperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
