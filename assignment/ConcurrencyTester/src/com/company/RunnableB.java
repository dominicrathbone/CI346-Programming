package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableB implements Runnable {

    int start;
    int end;
    int mersenneCount;

    public RunnableB(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD B");

        for (int i = start; i <= end; i++) {
            if (!Calculator.isMersenne(i)) {
                mersenneCount++;
            }
        }

        Debug.log("ENDED THREAD B");
    }
}
