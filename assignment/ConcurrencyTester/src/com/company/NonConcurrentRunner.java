package com.company;

import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 22/11/15.
 */
public class NonConcurrentRunner {

    List<Integer> randomNumbers;
    int number;

    public NonConcurrentRunner(List<Integer> randomNumbers, int number) {
        this.randomNumbers = randomNumbers;
        this.number = number;
    }

    public void run() {
        Iterator<Integer> randomNumbersIterator = randomNumbers.iterator();
        while (randomNumbersIterator.hasNext()) {
            Integer value = randomNumbersIterator.next();
            if(!Calculator.isFactor(value,number) ||
            !Calculator.isPrimeNumber(value) ||
            !Calculator.isMersenne(value) ||
            !Calculator.isPerfectNumber(value)) {
                randomNumbersIterator.remove();
            }
        }
        for(Integer i: randomNumbers) {
            System.out.println(i + "meets all conditions");
        }
    }

}
