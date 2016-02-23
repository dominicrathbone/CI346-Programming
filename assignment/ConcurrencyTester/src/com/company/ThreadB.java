package com.company;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadB implements Runnable {

    int start;
    int end;
    int mersenneCount;

    public ThreadB(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            if (!Calculator.isMersenne(i)) {
                mersenneCount++;
            }
        }
    }
}
