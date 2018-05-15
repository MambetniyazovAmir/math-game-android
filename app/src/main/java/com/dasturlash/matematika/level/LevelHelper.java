package com.dasturlash.matematika.level;

import android.util.Log;

/**
 * Created by QAREKEN on 5/6/2018.
 */

public class LevelHelper {
    private int level;
    private int sum;
    private int animationDuration;

    public LevelHelper() {
        animationDuration = 20000;
        level = 0;
        sum = 0;
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
