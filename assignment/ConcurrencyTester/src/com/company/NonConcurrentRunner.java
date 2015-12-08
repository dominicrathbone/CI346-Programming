package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drathbone on 22/11/15.
 */
public class NonConcurrentRunner {

    List<Student> students;

    public NonConcurrentRunner(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public void run() {
        Iterator<Student> it = students.listIterator();
        while(it.hasNext()) {
            Student student = it.next();
            if(student.getAge() <= 24 || student.getCourse() != Course.COMPUTER_SCIENCE) {
                it.remove();
            }
        }
        System.out.println("non = " + students.size());
    }

}
