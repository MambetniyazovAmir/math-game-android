package com.dasturlash.matematika;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dasturlash.matematika.game.GameActivity;
import com.dasturlash.matematika.owner.OwnerActivity;

public class MainActivity extends AppCompatActivity {
    public static final int MINUS_BUTTON = 1;
    public static final int PLUS_BUTTON = 2;
    public static final int DIVIDE_BUTTON = 3;
    public static final int MULTIPLY_BUTTON = 4;
    public static final String EXTRA_BUTTON_TYPE = "extraButtonType";

    private Button minusBtn, plusBtn, divideBtn, multiplyBtn;
    private boolean doubleBackToExitPressedOnce = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        divideBtn = findViewById(R.id.divideBtn);
        multiplyBtn = findViewById(R.id.multiplyBtn);
        ImageView ownerBtn = findViewById(R.id.author);
        minusBtn.setOnClickListener(listener);
        plusBtn.setOnClickListener(listener);
        divideBtn.setOnClickListener(listener);
        multiplyBtn.setOnClickListener(listener);
        ownerBtn.setOnClickListener(ownerBtnClickListener);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.easter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    View.OnClickListener ownerBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, OwnerActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            if (view == minusBtn) {
                intent.putExtra(EXTRA_BUTTON_TYPE, MINUS_BUTTON);
            } else if (view == plusBtn) {
                intent.putExtra(EXTRA_BUTTON_TYPE, PLUS_BUTTON);
            } else if (view == divideBtn) {
                intent.putExtra(EXTRA_BUTTON_TYPE, DIVIDE_BUTTON);
            } else if (view == multiplyBtn) {
                intent.putExtra(EXTRA_BUTTON_TYPE, MULTIPLY_BUTTON);
            } else {
                return;
            }
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Chiqish uchun yana bir marta bosing", Toast.LENGTH_SHORT).show();
        }
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
