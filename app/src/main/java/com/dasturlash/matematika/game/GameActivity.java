package com.dasturlash.matematika.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private int correctVariant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        variantA = findViewById(R.id.variantA);
        variantB = findViewById(R.id.variantB);
        variantC = findViewById(R.id.variantC);
        variantD = findViewById(R.id.variantD);
        linearLayout = findViewById(R.id.customViewContainer);
        nextTask(getIntent().getIntExtra(MainActivity.EXTRA_BUTTON_TYPE, 0));
        variantMaker = new VariantMaker(customView.getFirstNumber(), customView.getSecondNumber(), customView.getType(), this);
    }

    private void nextTask(int type) {
        if (customView != null ) {
            customView = null;
        }
        customView = new CustomView(GameActivity.this, type, null);
        linearLayout.addView(customView);
        startAnimation(customView);
    }

    private void startAnimation(CustomView view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation.setDuration(5000);
        view.startAnimation(animation);
    }

    @Override
    public void setVariants(int[] variants) {
        variantA.setText(variants[0]);
        variantB.setText(variants[1]);
        variantC.setText(variants[2]);
        variantD.setText(variants[3]);
    }
}
