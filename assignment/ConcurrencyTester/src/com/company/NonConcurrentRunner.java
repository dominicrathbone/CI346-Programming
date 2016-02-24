package com.company;

/**
 * Created by drathbone on 22/11/15.
 */
public class NonConcurrentRunner {

    int start;
    int end;
    int factorCount;
    int primeCount;
    int mersenneCount;
    int perfectNumberCount;


    public NonConcurrentRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for(int i = start; i <= end; i++) {
            if(!Calculator.isFactor(i,end)) {
                factorCount++;
            }
            if(!Calculator.isPrimeNumber(i)) {
                primeCount++;
            }
            if(!Calculator.isMersenne(i)) {
                mersenneCount++;
            }
            if(!Calculator.isPerfectNumber(i)) {
                perfectNumberCount++;
            }
        }
    }
}
