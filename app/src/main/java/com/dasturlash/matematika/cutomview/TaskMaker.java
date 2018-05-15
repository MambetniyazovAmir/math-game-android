package com.dasturlash.matematika.cutomview;

import com.dasturlash.matematika.MainActivity;
import java.util.Random;

/**
 * Created by QAREKEN on 4/30/2018.
 */

public class TaskMaker {
    private CustomViewInterface customViewInterface;
    private int type;
    private Integer firstNumber;
    private Integer secondNumber;
    private char[] operator;
    private Random random;

    TaskMaker(CustomViewInterface customViewInterface, int type) {
        this.customViewInterface = customViewInterface;
        this.type = type;
        random = new Random();
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
        int x = random.nextInt(30) + 5;
        secondNumber = random.nextInt(50/x) + 2;
        firstNumber = secondNumber*x;
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

    public int getType() {
        return type;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }
}
