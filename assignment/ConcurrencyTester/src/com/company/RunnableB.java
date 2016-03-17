package com.company;

public class RunnableB implements Runnable {
    int primeCount;
    long start;
    long end;

    public RunnableB(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for(long i = start; i < end; i++) {
            if (Calculator.isMersennePrime(i)) {
                primeCount++;
            }
        }
    }
}