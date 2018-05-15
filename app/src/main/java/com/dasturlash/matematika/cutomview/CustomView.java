package com.dasturlash.matematika.cutomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasturlash.matematika.R;

import java.util.Random;

/**
 * Created by QAREKEN on 4/29/2018.
 */

@SuppressLint("ViewConstructor")
public class CustomView extends LinearLayout implements CustomViewInterface {
    private TaskMaker taskMaker;
    private TextView text;
    private Integer firstNumber;
    private Integer secondNumber;
    private View view;
    private int type;

    @SuppressLint("ResourceAsColor")
    public CustomView(Context context, int type, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.type = type;
        taskMaker = new TaskMaker(this, type);
        view = LayoutInflater.from(context).inflate(R.layout.view_custom, this);
        text = view.findViewById(R.id.text);
        getTask();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        @SuppressLint("DrawAllocation") Random random = new Random();
        int width = 400;
        Log.d("width", width + "");
        int margin = random.nextInt(width) + 50;
        layoutParams.setMargins(margin, 0,0, 0);
        setLayoutParams(layoutParams);
    }

    private void getTask() {
        taskMaker.getTask();
    }

    @Override
    public void setTask(String task) {
        text.setText(task);
    }

    public Integer getFirstNumber() {
        firstNumber = taskMaker.getFirstNumber();
        return firstNumber;
    }

    public Integer getSecondNumber() {
        secondNumber = taskMaker.getSecondNumber();
        return secondNumber;
    }

    public int getType() {
        return type;
    }
}
