package com.company;

import java.util.List;

/**
 * Created by drathbone on 22/11/15.
 */
public class ParallelStreamRunner {

    List<Integer> randomNumbers;
    int number;

    public ParallelStreamRunner(List<Integer> randomNumbers, int number) {
        this.randomNumbers = randomNumbers;
        this.number = number;
    }

    public void run() {
        randomNumbers
                .parallelStream()
                .filter(i -> Calculator.isFactor(i, number))
                .filter(i -> Calculator.isPrimeNumber(i))
                .filter(i -> Calculator.isPerfectNumber(i))
                .filter(i -> Calculator.isMersenne(i))
                .forEach(i -> System.out.println(i + "meets all conditions"));
    }
}
