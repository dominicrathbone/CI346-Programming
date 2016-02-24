package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by drathbone on 22/11/15.
 */
public class ThreadPoolRunner {

    int start;
    int end;

    public ThreadPoolRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        executor.execute(new ThreadA(start, end));
        executor.execute(new ThreadB(start, end));
        executor.execute(new ThreadC(start, end));
        executor.execute(new ThreadD(start, end));
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
    }
}
