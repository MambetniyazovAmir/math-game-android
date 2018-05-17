package com.dasturlash.matematika.defeat;

import com.dasturlash.matematika.R;
import com.dasturlash.matematika.helper.SharedPrefHelper;

/**
 * Created by QAREKEN on 5/16/2018.
 */

class DefeatPresenter {
    private SharedPrefHelper sharedPrefHelper;
    private DefeatView defeatView;

    DefeatPresenter(SharedPrefHelper sharedPrefHelper, DefeatView defeatView) {
        this.sharedPrefHelper = sharedPrefHelper;
        this.defeatView = defeatView;
    }

    void updateScore(int type, int level) {
        int maxScore = sharedPrefHelper.getMaxScore(type);
        if (maxScore < level) {
            sharedPrefHelper.setMaxScore(type, level);
            defeatView.playMusic(R.raw.best_score);
        } else {
            defeatView.playMusic(R.raw.end);
        }
        sharedPrefHelper.setTotalScore(type, level + sharedPrefHelper.getTotalScore(type));
        defeatView.setBestScore(sharedPrefHelper.getMaxScore(type));
        defeatView.setYourScore(level);
        defeatView.setTotalScore(sharedPrefHelper.getTotalScore(type));
    }
}
