package com.company;

public class RunnableA implements Runnable {
    int primeCount;
    long number;
    public RunnableA(long number) {
        this.number = number;
    }
    public void run() {
        if(Calculator.isMersennePrime(number)) {
            primeCount++;
        }
    }
}