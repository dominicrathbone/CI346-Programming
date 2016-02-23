package com.company;

import java.io.*;

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

        for(int j = 0; j < runs; j++ ) {
            writeToCSV("results_" + start + "_" + end + "_" + ".csv", runTests(start, end));
        }
    }

    public static Double[] runTests(int start, int end) throws InterruptedException {

        NonConcurrentRunner nonConcurrentRunner = new NonConcurrentRunner(start, end);
        SerialStreamRunner serialStreamRunner = new SerialStreamRunner(start, end);
        ParallelStreamRunner parallelStreamRunner = new ParallelStreamRunner(start, end);
        RawMultithreadedRunner rawMultithreadedRunner = new RawMultithreadedRunner(start, end);
        ThreadPoolRunner threadPoolRunner = new ThreadPoolRunner(start, end);

        System.out.println("______________________RUN STARTED______________________");

        Debug.log("BEGIN NONCONCURRENT");
        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrentRunner.run();
        long nonConcurrentEndTime = System.nanoTime();
        Debug.log("END NONCONCURRENT");
        long nonConcurrentTotalTime = nonConcurrentEndTime - nonConcurrentStartTime;
        System.out.println(nonConcurrentTotalTime + " NON CONCURRENT");
        Debug.log("__________________________________");

        Debug.log("BEGIN RAW MULTITHREADED");
        long rawMultiThreadedStartTime = System.nanoTime();
        rawMultithreadedRunner.run();
        long rawMultiThreadedEndTime = System.nanoTime();
        Debug.log("END RAW MULTITHREADED");
        long rawMultiThreadedTotalTime = rawMultiThreadedEndTime - rawMultiThreadedStartTime;
        System.out.println(rawMultiThreadedTotalTime + " RAW MULTITHREADED");
        Debug.log("__________________________________");

        Debug.log("BEGIN THREADPOOLED");
        long threadPoolStartTime = System.nanoTime();
        threadPoolRunner.run();
        long threadPoolEndTime = System.nanoTime();
        Debug.log("END THREADPOOLED");
        long threadPoolTotalTime = threadPoolEndTime - threadPoolStartTime;
        System.out.println(threadPoolTotalTime + " THREAD POOL");
        Debug.log("__________________________________");

        Debug.log("BEGIN SERIAL STREAM");
        long serialStreamStartTime = System.nanoTime();
        serialStreamRunner.run();
        long serialStreamEndTime = System.nanoTime();
        Debug.log("END SERIAL STREAM");
        long serialStreamTotalTime = serialStreamEndTime - serialStreamStartTime;
        System.out.println(serialStreamTotalTime + " SERIAL STREAM");
        Debug.log("__________________________________");

        Debug.log("BEGIN PARALLEL STREAM");
        long parallelStreamStartTime = System.nanoTime();
        parallelStreamRunner.run();
        long parallelStreamEndTime = System.nanoTime();
        Debug.log("END PARALLEL STREAM");
        long parallelStreamTotalTime = parallelStreamEndTime - parallelStreamStartTime;
        System.out.println(parallelStreamTotalTime + " PARALLEL STREAM");
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


