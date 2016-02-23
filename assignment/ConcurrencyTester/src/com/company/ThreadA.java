package com.company;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadA implements Runnable {

    int start;
    int end;
    int factorCount;

    public ThreadA(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            if (!Calculator.isFactor(i, end)) {
                factorCount++;
            }
        }
    }
}
