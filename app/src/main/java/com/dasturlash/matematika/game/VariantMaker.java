package com.dasturlash.matematika.game;

import com.dasturlash.matematika.MainActivity;

import java.util.Random;

/**
 * Created by QAREKEN on 4/30/2018.
 */

class VariantMaker {
    private Integer firstNumber;
    private Integer secondNumber;
    private int type;
    private VariantListener variantListener;
    private Integer correctVariant;
    private Integer[] variants;
    private Random random;

    VariantMaker(Integer firstNumber, Integer secondNumber, int type, VariantListener variantListener) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.type = type;
        this.variantListener = variantListener;
        variants = new Integer[4];
        random = new Random();
    }

    void makeVariants() {
        setCorrectVariant();
        setVariants();
        mixVariants();
        variantListener.setVariants(variants);
    }

    private void mixVariants() {
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(3);
            int temp = variants[i];
            variants[i] = variants[index]; 
            variants[index] = temp;
        }
    }

    private void setVariants() {
        variants[0] = correctVariant;
        for (int i = 1; i < 4; i++) {
            boolean sign = random.nextBoolean();
            Integer distance;
            if (correctVariant <= 10) {
                distance = random.nextInt(9)+1;
                variants[i] = correctVariant + distance;
                continue;
            }
            distance = random.nextInt(correctVariant - 2) + 1;
            variants[i] = sign ? correctVariant + distance : correctVariant - distance;
        }
    }

    private void setCorrectVariant() {
        if (type == MainActivity.PLUS_BUTTON) {
            correctVariant = firstNumber + secondNumber;
        } else if (type == MainActivity.MINUS_BUTTON) {
            correctVariant = firstNumber - secondNumber;
        } else if (type == MainActivity.MULTIPLY_BUTTON) {
            correctVariant = firstNumber*secondNumber;
        } else if (type == MainActivity.DIVIDE_BUTTON) {
            correctVariant = firstNumber/secondNumber;
        }
    }

    public Integer getVariantA() {
        return variants[0];
    }

    public Integer getVariantB() {
        return variants[1];
    }

    public Integer getVariantC() {
        return variants[2];
    }

    public Integer getVariantD() {
        return variants[3];
    }

    Integer getCorrectVariant() {
        return correctVariant;
    }
}
