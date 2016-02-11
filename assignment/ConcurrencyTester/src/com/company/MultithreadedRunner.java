package com.company;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by drathbone on 22/11/15.
 */
public class MultithreadedRunner {

    List<Integer> randomNumberSet;
    int number;

    public MultithreadedRunner(List<Integer> randomNumberSet, int number) {
        this.randomNumberSet = randomNumberSet;
        this.number = number;
    }

    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new ThreadA(randomNumberSet, number));
        executor.execute(new ThreadB(randomNumberSet));
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
        for(Integer i: randomNumberSet) {
            System.out.println(i + "meets all conditions");
        }
    }
}
