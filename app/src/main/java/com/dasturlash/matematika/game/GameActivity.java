package com.dasturlash.matematika.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.R;
import com.dasturlash.matematika.cutomview.CustomView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout linearLayout = findViewById(R.id.customViewContainer);
        CustomView view = new CustomView(GameActivity.this, getIntent().getIntExtra(MainActivity.EXTRA_BUTTON_TYPE, 0), null);
        linearLayout.addView(view);
        startAnimation(view);
    }

    private void startAnimation(CustomView view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animation.setDuration(5000);
        view.startAnimation(animation);
    }
}
