package com.company;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by drathbone on 22/11/15.
 */
public class MultithreadedRunner {

    List<Integer> randomNumbers;
    int number;

    public MultithreadedRunner(List<Integer> randomNumbers, int number) {
        this.randomNumbers = randomNumbers;
        this.number = number;
    }

    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new ThreadA(randomNumbers, number));
        executor.execute(new ThreadB(randomNumbers, number));
        executor.execute(new ThreadC(randomNumbers, number));
        executor.execute(new ThreadD(randomNumbers, number));
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
        for(Integer i: randomNumbers) {
            System.out.println(i + "meets all conditions");
        }
    }
}
