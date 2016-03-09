package com.company;

import java.io.*;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length > 0 && !args[0].isEmpty()) {
            Debug.DEBUG = Boolean.valueOf(args[0]);
        }
        int start;
        int end;
        int runs;
        Console c = System.console();
        if(c != null) {
            start = Integer.parseInt(c.readLine("Enter start: "));
            end = Integer.parseInt(c.readLine("Enter end: "));
            runs = Integer.parseInt(c.readLine("Enter how many runs: "));
        } else {
            start = 1;
            end = 50000;
            runs = 10;
        }
        int cores = Runtime.getRuntime().availableProcessors();
        for(int j = 0; j < runs; j++ ) {
            writeToCSV("results_" + start + "_" + end + "_" + cores + "_CORES.csv", runTests(start, end));
        }
    }

    public static Double[] runTests(int start, int end) throws InterruptedException {

        NonConcurrentRunner nonConcurrentRunner = new NonConcurrentRunner(start, end);
        SerialStreamRunner serialStreamRunner = new SerialStreamRunner(start, end);
        ParallelStreamRunner parallelStreamRunner = new ParallelStreamRunner(start, end);
        RawMultithreadedRunner rawMultithreadedRunner = new RawMultithreadedRunner(start, end);
        ThreadPoolRunner threadPoolRunner = new ThreadPoolRunner(start, end);

        nonConcurrentRunner.run();
        rawMultithreadedRunner.run();
        threadPoolRunner.run();
        serialStreamRunner.run();
        parallelStreamRunner.run();

        System.out.println("______________________RUN STARTED______________________");

        Debug.log("BEGIN NONCONCURRENT");
        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrentRunner.run();
        long nonConcurrentEndTime = System.nanoTime();
        Debug.log("END NONCONCURRENT");
        long nonConcurrentTotalTime = nonConcurrentEndTime - nonConcurrentStartTime;
        System.out.println("NONCONCURRENT: " +nonConcurrentTotalTime);
        Debug.log("__________________________________");

        Debug.log("BEGIN RAW MULTITHREADED");
        long rawMultiThreadedStartTime = System.nanoTime();
        rawMultithreadedRunner.run();
        long rawMultiThreadedEndTime = System.nanoTime();
        Debug.log("END RAW MULTITHREADED");
        long rawMultiThreadedTotalTime = rawMultiThreadedEndTime - rawMultiThreadedStartTime;
        System.out.println("MULTITHREADED: " + rawMultiThreadedTotalTime);
        Debug.log("__________________________________");

        Debug.log("BEGIN THREADPOOLED");
        long threadPoolStartTime = System.nanoTime();
        threadPoolRunner.run();
        long threadPoolEndTime = System.nanoTime();
        Debug.log("END THREADPOOLED");
        long threadPoolTotalTime = threadPoolEndTime - threadPoolStartTime;
        System.out.println("THREADPOOLED: " + threadPoolTotalTime);
        Debug.log("__________________________________");

        Debug.log("BEGIN SERIAL STREAM");
        long serialStreamStartTime = System.nanoTime();
        serialStreamRunner.run();
        long serialStreamEndTime = System.nanoTime();
        Debug.log("END SERIAL STREAM");
        long serialStreamTotalTime = serialStreamEndTime - serialStreamStartTime;
        System.out.println("SERIAL STREAM: " + serialStreamTotalTime);
        Debug.log("__________________________________");

        Debug.log("BEGIN PARALLEL STREAM");
        long parallelStreamStartTime = System.nanoTime();
        parallelStreamRunner.run();
        long parallelStreamEndTime = System.nanoTime();
        Debug.log("END PARALLEL STREAM");
        long parallelStreamTotalTime = parallelStreamEndTime - parallelStreamStartTime;
        System.out.println("PARALLEL STREAM: " + parallelStreamTotalTime);
        Debug.log("__________________________________");

        System.out.println("_____________________RUN FINISHED______________________");

        Double nonConcurrentRawMultiThreadedRatio = (double) nonConcurrentTotalTime / rawMultiThreadedTotalTime;
        Double nonConcurrentThreadPoolRatio = (double) nonConcurrentTotalTime / threadPoolTotalTime;
        Double serialStreamParallelStreamRatio = (double) serialStreamTotalTime / parallelStreamTotalTime;

        return new Double[] {
                nonConcurrentRawMultiThreadedRatio,
                nonConcurrentThreadPoolRatio,
                serialStreamParallelStreamRatio
        };
    }

    public static void writeToCSV(String fileName, Double[] data) throws IOException {
        File dir = new File("output/");
        dir.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "/" + fileName, true));
        BufferedReader reader = new BufferedReader(new FileReader(dir + "/" + fileName));
        String header = "NC/RMT ratio, NC/TP ratio, SS/PS ratio";
        if(reader.readLine() == null) {
            writer.append(header);
            writer.append("\n");
        }
        for(int j = 0; j < data.length; j++) {
            writer.append(data[j].toString());
            writer.append(",");
        }
        writer.append("\n");
        writer.flush();
        writer.close();
    }

}


