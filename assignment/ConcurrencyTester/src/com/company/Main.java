package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Student> students = StudentGenerator.generateStudents();

        NonConcurrentRunner nonConcurrentRunner = new NonConcurrentRunner(students);
        SerialStreamRunner serialStreamRunner = new SerialStreamRunner(students);
        ParallelStreamRunner parallelStreamRunner = new ParallelStreamRunner(students);
        MultithreadedRunner multithreadedRunner = new MultithreadedRunner(students);

        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrentRunner.run();
        long nonConcurrentEndTime = System.nanoTime();

        long serialStreamStartTime = System.nanoTime();
        serialStreamRunner.run();
        long serialStreamEndTime = System.nanoTime();

        long parallelStreamStartTime = System.nanoTime();
        parallelStreamRunner.run();
        long parallelStreamEndTime = System.nanoTime();

        long multiThreadedStartTime = System.nanoTime();
        multithreadedRunner.run();
        long multiThreadedEndTime = System.nanoTime();

        long nonConcurrentTotalTime = nonConcurrentEndTime - nonConcurrentStartTime;
        long serialStreamTotalTime = serialStreamEndTime - serialStreamStartTime;
        long parallelStreamTotalTime = parallelStreamEndTime - parallelStreamStartTime;
        long multiThreadedTotalTime = multiThreadedEndTime - multiThreadedStartTime;

        System.out.println("non concurrent took " + nonConcurrentTotalTime + " seconds");
        System.out.println("serial stream took " + serialStreamTotalTime + " seconds");
        System.out.println("parallel stream took "  + parallelStreamTotalTime + " seconds");
        System.out.println("Multithreaded took "  + multiThreadedTotalTime + " seconds");
    }
}
