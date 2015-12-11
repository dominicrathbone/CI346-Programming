package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by drathbone on 08/12/15.
 */
public class StudentGenerator {

    public static List<Student> generateStudents(int size) {
        List<Student> students = new ArrayList<>();
        Random rng = new Random();
        Course[] courses = Course.values();

        for(int i = 0; i < size; i++) {
            UUID id = UUID.randomUUID();
            String name = "testStudent" + i;
            int age = rng.nextInt((60-18)+1) + 18;
            Course course = courses[rng.nextInt(courses.length)];
            int courseYear = rng.nextInt((5-1)+1) + 1;
            Student student = new Student(id, name, age, course, courseYear);
            students.add(i, student);
        }
        return students;
    }
}
