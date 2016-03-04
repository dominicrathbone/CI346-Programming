package com.company;


/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableA implements Runnable {

    int start;
    int end;
    int factorCount;

    public RunnableA(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD A");
        for (int i = start; i <= end; i++) {
            if (!Calculator.isFactor(i, end)) {
                factorCount++;
            }
        }
        Debug.log("ENDED THREAD A");
    }
}
