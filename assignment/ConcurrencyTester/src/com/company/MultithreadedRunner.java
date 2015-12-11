package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drathbone on 22/11/15.
 */
public class MultithreadedRunner {

    List<Student> students;

    public MultithreadedRunner(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public void run() throws InterruptedException {
        Thread threadA = new Thread(new ThreadA(students));
        Thread threadB = new Thread(new ThreadB(students));
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
