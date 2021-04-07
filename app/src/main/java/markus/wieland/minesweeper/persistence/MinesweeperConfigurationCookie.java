package markus.wieland.minesweeper.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import markus.wieland.minesweeper.view.MinesweeperStartScreen;

public class MinesweeperConfigurationCookie {

    private final SharedPreferences sharedPreferences;

    private static final String KEY_SIZE_X = "markus.wieland.minesweeper.persistence.KEY_SIZE_X";
    private static final String KEY_SIZE_Y = "markus.wieland.minesweeper.persistence.KEY_SIZE_Y";

    public MinesweeperConfigurationCookie(Context context) {
        sharedPreferences = context.getSharedPreferences("markus.wieland.minesweeper.persistence.CONFIGURATION", Context.MODE_PRIVATE);
    }

    public int getSizeX() {
        return sharedPreferences.getInt(KEY_SIZE_X, MinesweeperStartScreen.DEFAULT_SIZE_X);
    }

    public int getSizeY() {
        return sharedPreferences.getInt(KEY_SIZE_Y, MinesweeperStartScreen.DEFAULT_SIZE_Y);
    }

    public void saveSizeX(int sizeX) {
        storeInt(KEY_SIZE_X, sizeX);
    }

    public void saveSizeY(int sizeY) {
        storeInt(KEY_SIZE_Y, sizeY);
    }

    public void save(int sizeX, int sizeY) {
        saveSizeX(sizeX);
        saveSizeY(sizeY);
    }

    private void storeInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
