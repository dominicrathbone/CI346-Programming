package com.company;

import java.util.UUID;

/**
 * Created by drathbone on 04/12/15.
 */
public class Student {

    private UUID id;
    private String name;
    private int age;
    private Course course;
    private int courseYear;

    public Student(UUID id, String name, int age, Course course, int courseYear) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.courseYear = courseYear;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }
}
