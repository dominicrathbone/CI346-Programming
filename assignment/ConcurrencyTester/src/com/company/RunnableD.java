package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableD implements Runnable {

    int start;
    int end;
    int perfectNumberCount;

    public RunnableD(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD D");

        for (int i = start; i <= end; i++) {
            if (!Calculator.isPerfectNumber(i)) {
                perfectNumberCount++;
            }
        }

        Debug.log("ENDED THREAD D");
    }
}
