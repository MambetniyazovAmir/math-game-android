package com.dasturlash.matematika.cutomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasturlash.matematika.R;

/**
 * Created by QAREKEN on 4/29/2018.
 */

@SuppressLint("ViewConstructor")
public class CustomView extends LinearLayout implements CustomViewInterface {
    private TaskMaker taskMaker;
    private TextView text;
    private Integer firstNumber;
    private Integer secondNumber;
    private int type;

    @SuppressLint("ResourceAsColor")
    public CustomView(Context context, int type, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.type = type;
        taskMaker = new TaskMaker(this, type);
        View view = LayoutInflater.from(context).inflate(R.layout.view_custom, this);
        text = view.findViewById(R.id.text);
        getTask();
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
