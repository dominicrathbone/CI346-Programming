package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by drathbone on 22/11/15.
 */
public class ParallelStreamRunner {

    List<Student> students;

    public ParallelStreamRunner(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public void run() {
        students = students.stream()
                .parallel()
                .filter(s -> s.getAge() > 24)
                .filter(s -> s.getCourse() == Course.COMPUTER_SCIENCE)
                .collect(Collectors.toList());
        System.out.println("parallel = " + students.size());
    }
}
