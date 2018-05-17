package com.dasturlash.matematika.helper;

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
        this.level = -1;
        sum = 0;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        if (sum > level / 5 && animationDuration > 2000) {
            sum = 0;
            animationDuration -= 2000;
            Log.d("animationduration", animationDuration + "");
        } else {
            sum++;
        }
    }

    public Integer getAnimationDuration() {
        return animationDuration;
    }

}
