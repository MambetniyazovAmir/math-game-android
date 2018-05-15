package com.dasturlash.matematika.game;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.R;
import com.dasturlash.matematika.cutomview.CustomView;
import com.dasturlash.matematika.level.LevelHelper;

import java.util.Objects;
import java.util.Random;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        levelHelper = new LevelHelper();
        variantA = findViewById(R.id.variantA);
        variantB = findViewById(R.id.variantB);
        variantC = findViewById(R.id.variantC);
        variantD = findViewById(R.id.variantD);
        variantA.setOnClickListener(onClickListener);
        variantB.setOnClickListener(onClickListener);
        variantC.setOnClickListener(onClickListener);
        variantD.setOnClickListener(onClickListener);
        type = getIntent().getIntExtra(MainActivity.EXTRA_BUTTON_TYPE, 0);
        linearLayout = findViewById(R.id.customViewContainer);
        nextTask(type);
    }

    private void nextTask(int type) {
        if (customView != null ) {
            customView.clearAnimation();
            ((ViewManager) customView.getParent()).removeView(customView);
        }
        levelHelper.setLevel(levelHelper.getLevel() + 1);
        customView = new CustomView(GameActivity.this, type, null);
        variantMaker = new VariantMaker(customView.getFirstNumber(), customView.getSecondNumber(), customView.getType(), this);
        variantMaker.makeVariants();
        customView.requestLayout();
        linearLayout.addView(customView);
        startAnimation(customView);
        Log.d("level", levelHelper.getLevel().toString());
    }

    private void startAnimation(CustomView view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation.setDuration(levelHelper.getAnimationDuration());
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
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View view) {
            if (Objects.equals(((Button) view).getText().toString(), correctVariant.toString())) {
                nextTask(type);
            } else {
                //TODO utilg'anda ne boliwi usi jerge jaziladi
                
                return;
            }
        }
    };
}
