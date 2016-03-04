package com.company;

import java.util.stream.Stream;

/**
 * Created by drathbone on 22/11/15.
 */
public class ParallelStreamRunner {

    int start;
    int end;
    int factorCount;
    int primeCount;
    int mersenneCount;
    int perfectNumberCount;

    public ParallelStreamRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Stream.iterate(start, i -> i + 1)
            .limit(end)
            .parallel()
            .map(i -> isFactor(i, end))
            .map(i -> isPrime(i))
            .map(i -> isMersenne(i))
            .map(i -> isPerfect(i))
            .count();
    }

    public int isFactor(int value, int end) {
        if (Calculator.isFactor(value, end)) {
            factorCount++;
        }
        return value;
    }

    public int isPrime(int value) {
        if (Calculator.isPrimeNumber(value)) {
            primeCount++;
        }
        return value;
    }

    public int isMersenne(int value) {
        if (Calculator.isMersenne(value)) {
            mersenneCount++;
        }
        return value;
    }

    public int isPerfect(int value) {
        if (Calculator.isPerfectNumber(value)) {
            perfectNumberCount++;
        }
        return value;
    }
}
