package com.dasturlash.matematika.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by QAREKEN on 5/16/2018.
 */

public class SharedPrefHelper {
    private static String FILE_NAME = "preferences";
    private static String TYPES[] = {"" ,"minus", "plus", "divide", "multiply"};
    private static String TOTAL_SCORE[] = {"", "minusTotalScore", "plusTotalScore", "divideTotalScore", "multiplyTotalScore"};

    private SharedPreferences preferences;
    private Context context;

    public SharedPrefHelper(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setMaxScore(int type, int level) {
        preferences.edit().putInt(TYPES[type], level).apply();
    }

    public int getMaxScore(int type) {
        return preferences.getInt(TYPES[type], 0);
    }

    public void setTotalScore(int type, int score) {
        preferences.edit().putInt(TOTAL_SCORE[type], score).apply();
    }

    public int getTotalScore(int type) {
        return preferences.getInt(TOTAL_SCORE[type], 0);
    }
}
