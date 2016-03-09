package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableB implements Runnable {

    int number;
    int mersenneCount;

    public RunnableB(int number) {
        this.number = number;
    }

    public void run() {
        Debug.log("STARTED THREAD B");
        if (!Calculator.isMersenne(number)) {
            mersenneCount++;
        }
        Debug.log("ENDED THREAD B");
    }
}
