package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by drathbone on 22/11/15.
 */
public class ThreadPoolRunner {

    int start;
    int end;
    int cores = Runtime.getRuntime().availableProcessors();

    public ThreadPoolRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        executor.execute(new RunnableA(start, end));
        executor.execute(new RunnableB(start, end));
        executor.execute(new RunnableC(start, end));
        executor.execute(new RunnableD(start, end));
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
    }
}
