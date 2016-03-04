package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableC implements Runnable {

    int start;
    int end;
    int primeCount;

    public RunnableC(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD C");

        for (int i = start; i <= end; i++) {
            if (!Calculator.isPrimeNumber(i)) {
                primeCount++;
            }
        }

        Debug.log("ENDED THREAD C");
    }
}
