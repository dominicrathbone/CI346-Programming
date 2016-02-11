package com.company;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadB implements Runnable {

    List<Integer> randomNumberSet;
    int number;

    public ThreadB(List<Integer> randomNumberSet) {
        this.randomNumberSet = randomNumberSet;
        this.number = number;
    }

    public void run() {
        synchronized(randomNumberSet) {
            Iterator<Integer> randomNumbersIterator = randomNumberSet.iterator();
            while (randomNumbersIterator.hasNext()) {
            Integer value = randomNumbersIterator.next();
            if (!Calculator.isPerfectNumber(value) || !Calculator.isMersenne(value)) {
                    randomNumbersIterator.remove();
                }
            }
        }
    }
}
