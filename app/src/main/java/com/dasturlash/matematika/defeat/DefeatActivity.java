package com.dasturlash.matematika.defeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.dasturlash.matematika.R;

public class DefeatActivity extends AppCompatActivity {
    public static String CURRENT_TYPE = "type";
    public static String CURRENT_LEVEL = "level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defeat);
    }
}
