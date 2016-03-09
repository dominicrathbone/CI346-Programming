package com.company;

/**
 * Created by drathbone on 08/12/15.
 */
public class RunnableD implements Runnable {

    int number;
    int end;
    int factorCount;
    int primeCount;
    int mersenneCount;

    public RunnableD(int number, int end) {
        this.number = number;
        this.end = end;
    }

    public void run() {
        Debug.log("STARTED THREAD D");
            if(!Calculator.isFactor(number,end)) {
                factorCount++;
            }
            if(!Calculator.isPrimeNumber(number)) {
                primeCount++;
            }
            if(!Calculator.isMersenne(number)) {
                mersenneCount++;
            }
        Debug.log("ENDED THREAD D");
    }
}
