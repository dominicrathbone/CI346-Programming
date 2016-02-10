package com.company;

import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadC implements Runnable {

    List<Integer> randomNumbers;
    int number;

    public ThreadC(List<Integer> randomNumbers, int number) {
        this.randomNumbers = randomNumbers;
        this.number = number;
    }

    public void run() {
        synchronized(randomNumbers) {
            Iterator<Integer> randomNumbersIterator = randomNumbers.iterator();
            while (randomNumbersIterator.hasNext()) {
            Integer value = randomNumbersIterator.next();
            if (!Calculator.isPrimeNumber(value)) {
                    randomNumbersIterator.remove();
                }
            }
        }
    }
}
