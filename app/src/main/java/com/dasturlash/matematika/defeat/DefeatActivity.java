package com.dasturlash.matematika.defeat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.R;
import com.dasturlash.matematika.game.GameActivity;
import com.dasturlash.matematika.helper.SharedPrefHelper;

public class DefeatActivity extends AppCompatActivity implements DefeatView {
    public static String CURRENT_TYPE = "type";
    public static String CURRENT_LEVEL = "score";

    private ImageButton playAgainBtn;
    private ImageButton homeBtn;
    private TextView yourScore;
    private TextView bestScore;
    private TextView totalScore;
    private int type;
    private int score;
    private SharedPrefHelper sharedPrefHelper;
    private DefeatPresenter defeatPresenter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defeat);
        yourScore = findViewById(R.id.your_score);
        bestScore = findViewById(R.id.best_score);
        totalScore = findViewById(R.id.total_score);
        playAgainBtn = findViewById(R.id.reload_button);
        homeBtn = findViewById(R.id.home_button);
        type = getIntent().getIntExtra(CURRENT_TYPE, 0);
        score = getIntent().getIntExtra(CURRENT_LEVEL, 0);
        sharedPrefHelper = new SharedPrefHelper(this);
        defeatPresenter = new DefeatPresenter(sharedPrefHelper, this);
        defeatPresenter.updateScore(type, score);
        homeBtn.setOnClickListener(homeBtnClickListener);
        playAgainBtn.setOnClickListener(playAgainBtnClickListener);
    }



    private View.OnClickListener homeBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToHome();
        }
    };

    private View.OnClickListener playAgainBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            playAgain();
        }
    };

    private void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void playAgain() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainActivity.EXTRA_BUTTON_TYPE, type);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        goToHome();
    }

    @Override
    public void setBestScore(int score) {
        bestScore.setText("Eng yaxshi natija : " + score);
    }

    @Override
    public void setYourScore(int score) {
        yourScore.setText("Sizning natijangiz : " + score);
    }

    @Override
    public void setTotalScore(int score) {
        totalScore.setText("Umumiy natija : "  + score);
    }

    @Override
    public void playMusic(int resId) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resId);
        mediaPlayer.start();
    }

}
