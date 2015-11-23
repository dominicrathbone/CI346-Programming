package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        NonConcurrentRunner nonConcurrentRunner = new NonConcurrentRunner();
        ImplicitConcurrencyRunner implicitConcurrencyRunner = new ImplicitConcurrencyRunner();
        ExplicitConcurrencyRunner explicitConcurrencyRunner = new ExplicitConcurrencyRunner();

        long nonConcurrentStartTime = System.nanoTime();
        nonConcurrentRunner.run();
        long nonConcurrentEndTime = System.nanoTime();

        long nonConcurrentTotalTimeRun = nonConcurrentEndTime - nonConcurrentStartTime;

        long implicitStartTime = System.nanoTime();
        implicitConcurrencyRunner.run();
        long implicitEndTime = System.nanoTime();

        long implicitTotalTimeRun = implicitEndTime - implicitStartTime;

        long explicitStartTime = System.nanoTime();
        explicitConcurrencyRunner.run();
        long explicitEndTime = System.nanoTime();

        long explicitTotalTimeRun = explicitEndTime - explicitStartTime;

        System.out.println("non concurrent run took " + nonConcurrentTotalTimeRun + " seconds");
        System.out.println("implicit concurrent run took " + implicitTotalTimeRun + " seconds");
        System.out.println("explicit concurrent run took "  + explicitTotalTimeRun + " seconds" );
    }
}
