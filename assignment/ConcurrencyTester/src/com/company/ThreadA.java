package com.company;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadA implements Runnable {

    List<Integer> randomNumberSet;
    int number;

    public ThreadA(List<Integer> randomNumberSet, int number) {
        this.randomNumberSet = randomNumberSet;
        this.number = number;
    }

    public void run() {
        synchronized(randomNumberSet) {
            Iterator<Integer> randomNumbersIterator = randomNumberSet.iterator();
            while (randomNumbersIterator.hasNext()) {
            Integer value = randomNumbersIterator.next();
            if (!Calculator.isFactor(value, number) || !Calculator.isPrimeNumber(value)) {
                    randomNumbersIterator.remove();
                }
            }
        }
    }
}
