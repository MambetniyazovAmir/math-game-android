package com.dasturlash.matematika.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.R;
import com.dasturlash.matematika.cutomview.CustomView;
import com.dasturlash.matematika.defeat.DefeatActivity;
import com.dasturlash.matematika.helper.LevelHelper;


public class GameActivity extends AppCompatActivity implements VariantListener {
    private VariantMaker variantMaker;
    private CustomView customView;
    private LinearLayout linearLayout;
    private Button variantA;
    private Button variantB;
    private Button variantC;
    private Button variantD;
    private Integer correctVariant;
    private LevelHelper levelHelper;
    private Animation animation;
    private int type;
    private Context context;
    private Boolean isDefeated;
    private TextView scoreText;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        context = this;
        levelHelper = new LevelHelper();
        scoreText = findViewById(R.id.score_text);
        variantA = findViewById(R.id.variantA);
        variantB = findViewById(R.id.variantB);
        variantC = findViewById(R.id.variantC);
        variantD = findViewById(R.id.variantD);
        variantA.setOnClickListener(onClickListener);
        variantB.setOnClickListener(onClickListener);
        variantC.setOnClickListener(onClickListener);
        variantD.setOnClickListener(onClickListener);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation.setAnimationListener(animationListener);
        type = getIntent().getIntExtra(MainActivity.EXTRA_BUTTON_TYPE, 0);
        linearLayout = findViewById(R.id.customViewContainer);
        isDefeated = false;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click);
        nextTask(type);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    private void nextTask(int type) {
        if (customView != null) {
            animation.cancel();
            ((ViewManager) customView.getParent()).removeView(customView);
        }
        Integer currentLevel = levelHelper.getLevel();
        Log.d("currentLevel", currentLevel.toString());
        levelHelper.setLevel(currentLevel+1);
        scoreText.setText(levelHelper.getLevel().toString());
        if (customView == null) {
            customView = new CustomView(GameActivity.this, type, null);
        }
        customView.reset();
        variantMaker = new VariantMaker(customView.getFirstNumber(), customView.getSecondNumber(),
                customView.getType(), this);
        variantMaker.makeVariants();
        customView.requestLayout();
        linearLayout.addView(customView);
        startAnimation(customView);
        Log.d("isDefeated", isDefeated.toString());
    }

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            isDefeated = true;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
           onDefeat();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void startAnimation(CustomView view) {
        animation.setDuration(levelHelper.getAnimationDuration());
        isDefeated = false;
        animation.reset();
        view.startAnimation(animation);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setVariants(Integer[] variants) {
        variantA.setText(variants[0].toString());
        variantB.setText(variants[1].toString());
        variantC.setText(variants[2].toString());
        variantD.setText(variants[3].toString());
        correctVariant = variantMaker.getCorrectVariant();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (((Button) view).getText().toString().equals(correctVariant.toString())) {
                isDefeated = false;
                playMusic(R.raw.click);
                nextTask(type);
            } else {
                isDefeated = true;
                playMusic(R.raw.incorrect);
                onDefeat();
            }
        }
    };

    private void playMusic(int resId) {
        mediaPlayer.selectTrack(resId);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void onDefeat() {
        if (!isDefeated) {
            return;
        }
        if (animation != null) {
            animation.cancel();
        }
        if (customView != null && customView.getParent() != null) {
            ((ViewManager) customView.getParent()).removeView(customView);
        }
        Intent intent = new Intent(context, DefeatActivity.class);
        intent.putExtra(DefeatActivity.CURRENT_TYPE, type);
        intent.putExtra(DefeatActivity.CURRENT_LEVEL, levelHelper.getLevel());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
