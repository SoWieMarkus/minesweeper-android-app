package markus.wieland.minesweeper.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import markus.wieland.games.elements.Matrix;

public class MinesweeperGridAdapter extends BaseAdapter {

    private final Matrix<MinesweeperCellView> matrix;

    public MinesweeperGridAdapter(Matrix<MinesweeperCellView> matrix) {
        this.matrix = matrix;
    }

    @Override
    public int getCount() {
        return matrix.getSizeX() * matrix.getSizeY();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //TODO implement new matrix.get(i) method
        int j = 0;
        for (MinesweeperCellView minesweeperCellView : matrix) {
            if (j == i) return minesweeperCellView;
            j++;
        }
        return null;
    }
}
