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
        threads.add(new Thread(new RunnableA(start, end)));
        threads.add(new Thread(new RunnableB(start, end)));
        threads.add(new Thread(new RunnableC(start, end)));
        threads.add(new Thread(new RunnableD(start, end)));
        for(Thread thread: threads) {
            thread.start();
        }
        for(Thread thread: threads) {
            thread.join();
        }
    }
}
