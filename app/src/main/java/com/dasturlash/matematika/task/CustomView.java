package com.dasturlash.matematika.task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
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
        Display display = ((Activity) view.getContext()).getWindowManager().getDefaultDisplay();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        @SuppressLint("DrawAllocation") Random random = new Random();
        int width = display.getWidth();
        Log.d("width", width + "");
        int margin = random.nextInt(width - 250) + 50;
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
        Integer firstNumber = taskMaker.getFirstNumber();
        return firstNumber;
    }

    public Integer getSecondNumber() {
        Integer secondNumber = taskMaker.getSecondNumber();
        return secondNumber;
    }

    public int getType() {
        return type;
    }

    public void reset() {
        taskMaker.getTask();
    }
}
