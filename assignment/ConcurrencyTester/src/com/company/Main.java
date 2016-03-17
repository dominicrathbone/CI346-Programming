package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Main {

    public static int START;
    public static int END;
    public static int CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException, IOException {
        int runs;

        Console c = System.console();
        if(c != null) {
            if(Boolean.parseBoolean(c.readLine("Choose own runs? (true/false)"))) {
                START = Integer.parseInt(c.readLine("Enter start: "));
                END = Integer.parseInt(c.readLine("Enter end: "));
                runs = Integer.parseInt(c.readLine("Enter how many runs: "));
                for(int j = 0; j < runs; j++ ) {
                    writeToCSV("results_" + CORES + "_CORES.csv", runTests());
                }
            } else {
                runs = 5;
                for(int i = 10; i <= 100000; i = i * 10) {
                    START = 100 * i;
                    END = 200 * i;
                    for (int j = 0; j < runs; j++) {
                        writeToCSV("results_" + CORES + "_CORES.csv", runTests());
                    }
                }
            }
        } else {
            START = 800_000_000;
            END = START + 1_000_000;
            runs = 10;
            for(int j = 0; j < runs; j++ ) {
                writeToCSV("results_" + CORES + "_CORES.csv", runTests());
            }
        }
    }

    public static Double[] runTests() throws InterruptedException {

        System.out.println("______________________RUN STARTED______________________");

        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrent();
        long nonConcurrentEndTime = System.nanoTime();
        double nonConcurrentTotalTime = (double) (nonConcurrentEndTime - nonConcurrentStartTime)/1_000_000_000;
        System.out.printf("NONCONCURRENT: %6.4f secs\n", nonConcurrentTotalTime);

        long serialStreamStartTime = System.nanoTime();
        serialStream();
        long serialStreamEndTime = System.nanoTime();
        double serialStreamTotalTime = (double) (serialStreamEndTime - serialStreamStartTime)/1_000_000_000;
        System.out.printf("SERIAL STREAM:  %6.4f secs\n", serialStreamTotalTime);

        long parallelStreamStartTime = System.nanoTime();
        parallelStream();
        long parallelStreamEndTime = System.nanoTime();
        double parallelStreamTotalTime = (double) (parallelStreamEndTime - parallelStreamStartTime)/1_000_000_000;
        System.out.printf("PARALLEL STREAM:  %6.4f secs\n", parallelStreamTotalTime);

        long rawMultiThreadedStartTime = System.nanoTime();
        multiThreaded2();
        long rawMultiThreadedEndTime = System.nanoTime();
        double rawMultiThreadedTotalTime = (double) (rawMultiThreadedEndTime - rawMultiThreadedStartTime)/1_000_000_000;
        System.out.printf("MULTITHREADED:  %6.4f secs\n", rawMultiThreadedTotalTime);

        long threadPoolStartTime = System.nanoTime();
        threadPool();
        long threadPoolEndTime = System.nanoTime();
        double threadPoolTotalTime = (double) (threadPoolEndTime - threadPoolStartTime)/1_000_000_000;
        System.out.printf("THREADPOOLED:  %6.4f secs\n", threadPoolTotalTime);

        Double nonConcurrentSerialStreamRatio = nonConcurrentTotalTime / serialStreamTotalTime;
        Double nonConcurrentRawMultiThreadedRatio = nonConcurrentTotalTime / rawMultiThreadedTotalTime;
        Double nonConcurrentThreadPoolRatio =  nonConcurrentTotalTime / threadPoolTotalTime;
        Double nonConcurrentParallelStreamRatio = nonConcurrentTotalTime / parallelStreamTotalTime;
        System.out.printf("NONCONCURRENT/MULTITHREADED RATIO: 1:%5.3f\n", nonConcurrentRawMultiThreadedRatio);
        System.out.printf("NONCONCURRENT/THREAD POOL RATIO: 1:%5.3f\n", nonConcurrentThreadPoolRatio);
        System.out.printf("NONCONCURRENT/SERIAL STREAM RATIO: 1:%5.3f\n", nonConcurrentSerialStreamRatio);
        System.out.printf("NONCONCURRENT/PARALLEL STREAM: 1:%5.3f\n", nonConcurrentParallelStreamRatio);

        System.out.println("_____________________RUN FINISHED______________________");

        return new Double[] {
                nonConcurrentRawMultiThreadedRatio,
                nonConcurrentThreadPoolRatio,
                nonConcurrentSerialStreamRatio,
                nonConcurrentParallelStreamRatio
        };
    }

    public static void writeToCSV(String fileName, Double[] data) throws IOException {
        File dir = new File("output/");
        dir.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "/" + fileName, true));
        BufferedReader reader = new BufferedReader(new FileReader(dir + "/" + fileName));
        String header = "START VALUE, END VALUE, NC/MT ratio, NC/TP ratio, NC/SS ratio, NC/PS ratio";
        if(reader.readLine() == null) {
            writer.append(header);
            writer.append("\n");
        }
        writer.append(START + ",");
        writer.append(END + ",");
        for(int j = 0; j < data.length; j++) {
            writer.append(data[j].toString());
            writer.append(",");
        }
        writer.append("\n");
        writer.flush();
        writer.close();
    }

    public static void nonConcurrent() {
        for ( long n = START; n < END; n++ ) {
            int primeCount = 0;
            if (Calculator.isMersennePrime(n)) {
                primeCount++;
            }
        }
    }

    public static void serialStream() {
        long primeCount = Stream
                .iterate(START, n -> n + 1)
                .limit(END-START)
                .filter( n -> Calculator.isMersennePrime(n) )
                .count();
    }

    public static void parallelStream() {
        long primeCount = Stream
                .iterate(START, n -> n + 1)
                .limit(END-START)
                .parallel()
                .filter(n -> Calculator.isMersennePrime(n) )
                .count();
    }

    public static void multiThreaded() {
        List<Thread> threads = new ArrayList<>();
        for (long n = START; n < END; n++) {
            Thread thread = new Thread(new RunnableA(n));
            thread.start();
            threads.add(thread);
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void multiThreaded2() {
        List<Thread> threads = new ArrayList<>();
        long subsequence = (END - START) / CORES;
        for ( long n = START; n < END; n = n+subsequence) {
            Thread thread = new Thread(new RunnableB(n, n+subsequence));
            thread.start();
            threads.add(thread);
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void threadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(CORES);
        ArrayList<Future<Integer>> futures = new ArrayList<>();

        for ( long n = START; n < END; n++ ) {
            final long number = n;
            Future<Integer> future = executorService.submit (
                    () ->
                    {
                        if ( Calculator.isMersennePrime(number) ) return 1;
                        return 0;
                    }
            );
            futures.add(future);
        }

        int primeCount = 0;
        for (Future<Integer> future : futures) {
            try
            {
                primeCount += future.get();
            } catch (Exception e)
            {
                System.err.println("Should not happen");
            }
        }

        executorService.shutdown();

    }

}




