package com.company;


/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableA implements Runnable {

    int number;
    int end;
    int factorCount;

    public RunnableA(int number, int end) {
        this.number = number;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD A");
        if (!Calculator.isFactor(number, end)) {
            factorCount++;
        }
        Debug.log("ENDED THREAD A");
    }
}
