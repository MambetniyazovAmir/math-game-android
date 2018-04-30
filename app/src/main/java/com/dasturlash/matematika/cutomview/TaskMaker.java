package com.dasturlash.matematika.cutomview;
import android.util.Log;

import com.dasturlash.matematika.MainActivity;
import com.dasturlash.matematika.holders.NumberHolder;
import java.util.Random;

/**
 * Created by QAREKEN on 4/30/2018.
 */

public class TaskMaker {
    private CustomViewInterface customViewInterface;
    private int type;
    private Integer firstNumber;
    private Integer secondNumber;
    private int[] variants;
    private char[] operator;
    private Random random;

    TaskMaker(CustomViewInterface customViewInterface, int type) {
        this.customViewInterface = customViewInterface;
        this.type = type;
        random = new Random();
        variants = new int[4];
        operator = new char[]{'0', '-', '+', '÷', '×'};
    }

    private void makeTaskForMinus() {
        firstNumber = random.nextInt(50)+5;
        secondNumber = random.nextInt(firstNumber)+1;
    }

    private void makeTaskForPlus() {
        firstNumber = random.nextInt(40)+5;
        secondNumber = random.nextInt(40)+5;
    }

    private void makeTaskForMulitply() {
        firstNumber = random.nextInt(20) + 5;
        secondNumber = random.nextInt(100/firstNumber)+2;
    }

    private void makeTaskFotDivide() {
        NumberHolder numberHolder = NumberHolder.getInstance();
        int index = random.nextInt(numberHolder.getDividends().size()-1);
        firstNumber = numberHolder.getDividends().get(index);
        index = random.nextInt(numberHolder.getDividers(firstNumber).size()-1);
        secondNumber = numberHolder.getDividers(firstNumber).get(index);
        Log.d("dividers", firstNumber.toString() + "->" + numberHolder.getDividers(firstNumber));
    }

    void getTask() {
        if (type == MainActivity.MINUS_BUTTON) {
            makeTaskForMinus();
        } else if (type == MainActivity.PLUS_BUTTON) {
            makeTaskForPlus();
        } else if (type == MainActivity.DIVIDE_BUTTON) {
            makeTaskFotDivide();
        } else if (type == MainActivity.MULTIPLY_BUTTON) {
            makeTaskForMulitply();
        } else {
            return;
        }
        String task = firstNumber.toString() + operator[type] + secondNumber.toString();
        customViewInterface.setTask(task);
    }
}
