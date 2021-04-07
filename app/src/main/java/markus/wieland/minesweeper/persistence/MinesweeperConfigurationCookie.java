package markus.wieland.minesweeper.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import markus.wieland.minesweeper.view.MinesweeperStartScreen;

public class MinesweeperConfigurationCookie {

    private final SharedPreferences sharedPreferences;

    private static final String KEY_SIZE_X = "markus.wieland.minesweeper.persistence.KEY_SIZE_X";

    public MinesweeperConfigurationCookie(Context context) {
        sharedPreferences = context.getSharedPreferences("markus.wieland.minesweeper.persistence.CONFIGURATION", Context.MODE_PRIVATE);
    }

    public int getSizeX() {
        return sharedPreferences.getInt(KEY_SIZE_X, MinesweeperStartScreen.DEFAULT_SIZE_X);
    }

    public void save(int sizeX) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SIZE_X, sizeX);
        editor.apply();
    }


}
