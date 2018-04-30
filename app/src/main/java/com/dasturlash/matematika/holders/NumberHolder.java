package com.dasturlash.matematika.holders;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by QAREKEN on 4/30/2018.
 */

public final class NumberHolder {
    public static final int ERATOSFEN_NUMBER_COUNT = 100;

    private static NumberHolder instance = null;
    private ArrayList<Integer> dividends;
    private ArrayMap<Integer, ArrayList<Integer>> dividers;
    private boolean primes[];
    private boolean isDividend[];

    private NumberHolder() {
        primes = new boolean[ERATOSFEN_NUMBER_COUNT+2];
        isDividend = new boolean[ERATOSFEN_NUMBER_COUNT+2];
        dividends = new ArrayList<>();
        dividers = new ArrayMap<>();
        eratosfenSieve(ERATOSFEN_NUMBER_COUNT);
        setDividers();
    }

    public static NumberHolder getInstance() {
        if (instance == null) {
            instance = new NumberHolder();
        }
        return instance;
    }

    // Erotosfen Sieve algoritmi bilan tub bo'lmagan sonlar va qaysi son qaysi sonlarga
    // bo'linishini aniqlab olamiz
    private void eratosfenSieve(int count) {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 2; i <= count; i++) {
            if (primes[i]) {
                for (int j = i*i; j <= count; j+=i) {
                    primes[j] = false;
                    if (!isDividend[j]) {
                        isDividend[j] = true;
                        dividends.add(j);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getDividers(Integer number) {
        return dividers.get(number);
    }

    private void setDividers() {
        ArrayList<Integer> list;
        for (int i = 2; i <= ERATOSFEN_NUMBER_COUNT; i++) {
            if (isDividend[i]) {
                list = new ArrayList<>();
                for (int j = 2; j * j <= i; j++) {
                    if (i%j == 0) {
                        list.add(j);
                        if (j*j != i) {
                            list.add(i/j);
                        }
                    }
                }
                dividers.put(i, list);
            }
        }
    }

    public ArrayList<Integer> getDividends() {
        return dividends;
    }

}
