package com.company;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadD implements Runnable {

    int start;
    int end;
    int perfectNumberCount;

    public ThreadD(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            if (!Calculator.isPerfectNumber(i)) {
                perfectNumberCount++;
            }
        }
    }
}
