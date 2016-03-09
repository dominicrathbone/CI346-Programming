package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        ArrayList<Future> futures = new ArrayList<>();

        for ( int n = start; n < end; n++ )
        {
            futures.add(executor.submit(new RunnableA(n, end)));
            futures.add(executor.submit(new RunnableB(n)));
            futures.add(executor.submit(new RunnableC(n)));
        }
        for (Future future : futures)
        {
            try
            {
                future.get();
            } catch (Exception e)
            {
                System.err.println("ERROR");
            }
        }
        executor.shutdown();
    }
}
