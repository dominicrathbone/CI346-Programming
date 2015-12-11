package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Student> students;

        Long[][] results = new Long[4][4];
        students = StudentGenerator.generateStudents(100);
        results[0] = runTests(students);
        students = StudentGenerator.generateStudents(1000);
        results[1] = runTests(students);
        students = StudentGenerator.generateStudents(10000);
        results[2] = runTests(students);
        students = StudentGenerator.generateStudents(100000);
        results[3] = runTests(students);

        generateCSV(results);
    }

    public static Long[] runTests(List<Student> students) throws InterruptedException {
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

        return new Long[] {nonConcurrentTotalTime, serialStreamTotalTime, parallelStreamTotalTime, multiThreadedTotalTime};
    }

    public static void generateCSV(Long[][] data) throws IOException {
        FileWriter writer = new FileWriter("results.csv");

        writer.append("Number of Students,Non Concurrent, Serial Stream, Parallel Stream, Multithreaded");
        writer.append("\n");
        for(int j = 0; j < 4; j++) {
            if(j == 0) {
                writer.append("100");
            }
            if(j == 1) {
                writer.append("1000");
            }
            if(j == 2) {
                writer.append("10000");
            }
            if(j == 3) {
                writer.append("100000");
            }
            writer.append(",");
            for(int i = 0; i < 4; i++) {
                writer.append(data[j][i].toString());
                writer.append(",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }
}
