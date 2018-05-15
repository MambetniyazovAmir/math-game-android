package com.dasturlash.matematika.level;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by QAREKEN on 5/6/2018.
 */

public class LevelHelper {
    private static String FILE_NAME = "preferences";
    private static String TYPES[] = {"" ,"minus", "plus", "divide", "multiply"};

    private int level;
    private int sum;
    private int animationDuration;
    private SharedPreferences preferences;
    private Context context;

    public LevelHelper(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        animationDuration = 20000;
        level = 0;
        sum = 0;
    }

    public void setMaxLevel(int type, int level) {
        preferences.edit().putInt(TYPES[type], level).apply();
    }

    public void getMaxLevel(int type) {
        preferences.getInt(TYPES[type], 0);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        if (sum > level/5 && animationDuration > 0) {
            sum = 0;
            animationDuration -= 1000;
            Log.d("animationduration", animationDuration + "");
        } else {
            sum ++;
        }
    }

    public Integer getAnimationDuration() {
        return animationDuration;
    }

}
