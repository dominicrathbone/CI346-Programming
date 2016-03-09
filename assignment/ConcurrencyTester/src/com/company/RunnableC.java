package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableC implements Runnable {

    int number;
    int primeCount;

    public RunnableC(int number) {
        this.number = number;
    }

    public void run() {
        Debug.log("STARTED THREAD C");
        if (!Calculator.isPrimeNumber(number)) {
            primeCount++;
        }
        Debug.log("ENDED THREAD C");
    }
}
