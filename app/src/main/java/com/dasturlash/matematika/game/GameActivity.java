package com.dasturlash.matematika.game;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.R;
import com.dasturlash.matematika.cutomview.CustomView;

public class GameActivity extends AppCompatActivity implements VariantListener {
    private VariantMaker variantMaker;
    private CustomView customView;
    private LinearLayout linearLayout;
    private Button variantA;
    private Button variantB;
    private Button variantC;
    private Button variantD;
    private Integer correctVariant;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        customView = new CustomView(GameActivity.this, type, null);
        variantMaker = new VariantMaker(customView.getFirstNumber(), customView.getSecondNumber(), customView.getType(), this);
        variantMaker.makeVariants();
        linearLayout.addView(customView);
        startAnimation(customView);
    }

    private void startAnimation(CustomView view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation.setDuration(20000);
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
            if (((Button) view).getText() == correctVariant.toString()) {
                nextTask(type);
            } else {
                return;
            }
        }
    };
}
