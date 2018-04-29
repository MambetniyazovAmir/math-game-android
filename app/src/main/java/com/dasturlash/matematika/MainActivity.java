package com.dasturlash.matematika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dasturlash.matematika.game.GameActivity;

public class MainActivity extends AppCompatActivity {
    public static final int MINUS_BUTTON = 1;
    public static final int PLUS_BUTTON = 2;
    public static final int DIVIDE_BUTTON = 3;
    public static final int MULTIPLY_BUTTON = 4;
    public static final String EXTRA_BUTTON_TYPE = "extraButtonType";

    private Button minusBtn, plusBtn, divideBtn, multiplyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        divideBtn = findViewById(R.id.divideBtn);
        multiplyBtn = findViewById(R.id.multiplyBtn);
        minusBtn.setOnClickListener(listener);
        plusBtn.setOnClickListener(listener);
        divideBtn.setOnClickListener(listener);
        multiplyBtn.setOnClickListener(listener);
    }

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
}
