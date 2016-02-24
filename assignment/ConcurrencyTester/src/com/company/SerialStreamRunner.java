package com.company;

import java.util.stream.Stream;

/**
 * Created by drathbone on 22/11/15.
 */
public class SerialStreamRunner {

    int start;
    int end;
    int factorCount;
    int primeCount;
    int mersenneCount;
    int perfectNumberCount;

    public SerialStreamRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Stream
        .iterate(start, i -> i++)
        .limit(end)
        .filter(i -> isFactor(i, end))
        .filter(i -> isPrime(i))
        .filter(i -> isMersenne(i))
        .filter(i -> isPerfect(i))
        .close();
    }

    public boolean isFactor(int value, int end) {
        if(Calculator.isFactor(value, end)) {
            factorCount++;
            return true;
        }
        return false;
    }

    public boolean isPrime(int value) {
        if(Calculator.isPrimeNumber(value)) {
            primeCount++;
            return true;
        }
        return false;
    }

    public boolean isMersenne(int value) {
        if(Calculator.isMersenne(value)) {
            mersenneCount++;
            return true;
        }
        return false;
    }

    public boolean isPerfect(int value) {
        if(Calculator.isPerfectNumber(value)) {
            perfectNumberCount++;
            return true;
        }
        return false;
    }


}
