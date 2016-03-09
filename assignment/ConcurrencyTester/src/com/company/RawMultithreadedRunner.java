package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drathbone on 22/11/15.
 */
public class RawMultithreadedRunner {

    int start;
    int end;

    public RawMultithreadedRunner(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() throws InterruptedException {
        List<Thread> threads = new ArrayList();

        for (int n = start; n < end; n++ ) {
            Thread factor = new Thread(new RunnableA(n, end));
            factor.start();
            threads.add(factor);

            Thread mersenne = new Thread(new RunnableB(n));
            mersenne.start();
            threads.add(mersenne);

            Thread prime = new Thread(new RunnableC(n));
            prime.start();
            threads.add(prime);

            for(Thread thread: threads) {
                thread.join();
            }
            threads.removeAll(threads);
        }
    }
}
