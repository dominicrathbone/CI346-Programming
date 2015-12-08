package com.company;

import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 08/12/15.
 */
public class ThreadA implements Runnable {

    List<Student> students;

    public ThreadA(List<Student> students) {
        this.students = students;
    }

    public void run() {
        synchronized(students) {
            Iterator<Student> it = students.listIterator();
            while(it.hasNext()) {
                Student student = it.next();
                if (student.getAge() <= 24) {
                    it.remove();
                }
            }
        }
    }
}
