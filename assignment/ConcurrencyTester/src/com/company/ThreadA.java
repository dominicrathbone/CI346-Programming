package com.company;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadA implements Runnable {

    List<Integer> randomNumbers;
    int number;

    public ThreadA(List<Integer> randomNumbers, int number) {
        this.randomNumbers = randomNumbers;
        this.number = number;
    }

    public void run() {
        synchronized(randomNumbers) {
            Iterator<Integer> randomNumbersIterator = randomNumbers.iterator();
            while (randomNumbersIterator.hasNext()) {
            Integer value = randomNumbersIterator.next();
            if (!Calculator.isFactor(value, number)) {
                    randomNumbersIterator.remove();
                }
            }
        }
    }
}
