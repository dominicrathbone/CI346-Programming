package com.company;

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
        Thread threadA = new Thread(new ThreadA(start, end));
        Thread threadB = new Thread(new ThreadB(start, end));
        Thread threadC = new Thread(new ThreadC(start, end));
        Thread threadD = new Thread(new ThreadD(start, end));
        threadA.run();
        threadB.run();
        threadC.run();
        threadD.run();
    }
}
