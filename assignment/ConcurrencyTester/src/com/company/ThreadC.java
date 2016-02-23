package com.company;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadC implements Runnable {

    int start;
    int end;
    int primeCount;

    public ThreadC(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            if (!Calculator.isPrimeNumber(i)) {
                primeCount++;
            }
        }
    }
}
