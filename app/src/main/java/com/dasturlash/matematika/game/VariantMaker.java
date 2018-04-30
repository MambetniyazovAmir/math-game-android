package com.dasturlash.matematika.game;

/**
 * Created by QAREKEN on 4/30/2018.
 */

public class VariantMaker {
    private Integer firstNumber;
    private Integer secondNumber;
    private int type;
    private VariantListener variantListener;

    public VariantMaker(Integer firstNumber, Integer secondNumber, int type, VariantListener variantListener) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.type = type;
        this.variantListener = variantListener;
    }

}
