package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length > 0 && !args[0].isEmpty()) {
            Debug.DEBUG = Boolean.valueOf(args[0]);
        }
        Console c = System.console();
        int length = Integer.parseInt(c.readLine("Enter set length: "));
        int range = Integer.parseInt(c.readLine("Enter set range: "));
        int runs = Integer.parseInt(c.readLine("Enter how many runs: "));
        for(int j = 0; j < runs; j++ ) {
            writeToCSV("results_" + length + "_" + range + ".csv", runTests(length, range));
        }
    }

    public static Double[] runTests(int length, int range) throws InterruptedException {
        List<Integer> randomNumbers = generateRandomNumbers(length, range);
        Integer randomInteger = new Random().nextInt(range);
        NonConcurrentRunner nonConcurrentRunner = new NonConcurrentRunner(new ArrayList<>(randomNumbers), randomInteger);
        SerialStreamRunner serialStreamRunner = new SerialStreamRunner(new ArrayList<>(randomNumbers), randomInteger);
        ParallelStreamRunner parallelStreamRunner = new ParallelStreamRunner(new ArrayList<>(randomNumbers), randomInteger);
        MultithreadedRunner multithreadedRunner = new MultithreadedRunner(new ArrayList<>(randomNumbers), randomInteger);

        System.out.println("______________________RUN STARTED______________________");

        Debug.log("BEGIN NONCONCURRENT");
        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrentRunner.run();
        long nonConcurrentEndTime = System.nanoTime();
        Debug.log("END NONCONCURRENT");
        long nonConcurrentTotalTime = nonConcurrentEndTime - nonConcurrentStartTime;
        System.out.println(nonConcurrentTotalTime + " NON CONCURRENT");
        Debug.log("__________________________________");

        Debug.log("BEGIN MULTITHREADED");
        long multiThreadedStartTime = System.nanoTime();
        multithreadedRunner.run();
        long multiThreadedEndTime = System.nanoTime();
        Debug.log("END MULTITHREADED");
        long multiThreadedTotalTime = multiThreadedEndTime - multiThreadedStartTime;
        System.out.println(multiThreadedTotalTime + " MULTITHREADED");
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

        Double nonConcurrentMultiThreadedRatio = (double) nonConcurrentTotalTime / multiThreadedTotalTime;
        Double serialStreamParallelStreamRatio = (double) serialStreamTotalTime / parallelStreamTotalTime;

        return new Double[] {nonConcurrentMultiThreadedRatio, serialStreamParallelStreamRatio};
    }

    private static List<Integer> generateRandomNumbers(int length, int range) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random rng = new Random();
        for(int i = 0; i < length; i++) {
            randomNumbers.add(rng.nextInt(range)+1);
        }
        return randomNumbers;
    }

    public static void writeToCSV(String fileName, Double[] data) throws IOException {
        File dir = new File("output/");
        dir.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dir + fileName, true));
        BufferedReader reader = new BufferedReader(new FileReader(dir + fileName));
        String header = "NC/MT ratio, SS/PS ratio";
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


